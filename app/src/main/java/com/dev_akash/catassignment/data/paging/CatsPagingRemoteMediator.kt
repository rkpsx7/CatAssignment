package com.dev_akash.catassignment.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.dev_akash.catassignment.data.local.CatAppDB
import com.dev_akash.catassignment.data.model.CatsDto
import com.dev_akash.catassignment.data.model.RemoteKeys
import com.dev_akash.catassignment.data.remote.CatApi
import com.dev_akash.catassignment.utils.network_utils.validate
@OptIn(ExperimentalPagingApi::class)
class CatsPagingRemoteMediator(
    private val db: CatAppDB,
    private val api: CatApi,
    private val breedId: String?
) : RemoteMediator<Int, CatsDto>() {

    private val catsDao = db.getCatsDao()
    private val remoteKeysDao = db.getRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, CatsDto>
    ): MediatorResult {
        return try {
            val currPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKey?.prevPage
                        ?: return MediatorResult.Success(remoteKey != null)
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage
                        ?: return MediatorResult.Success(remoteKey != null)
                    nextPage
                }
            }

            val response = api.getCatList(currPage,breedId).validate()
            val endOfPaginationReached = if (breedId.isNullOrBlank()) response.data.isNullOrEmpty() else true

            val prevPage = if (currPage == 1) null else currPage - 1
            val nextPage = if (endOfPaginationReached) null else currPage + 1


            response.data?.let { cats ->
                db.withTransaction {
                    clearDBIfRefresh(loadType,breedId)

                    catsDao.upsertCats(cats = cats)
                    remoteKeysDao.upsertKeys(cats.map { RemoteKeys(it.id, prevPage, nextPage) })

                }
            }

            MediatorResult.Success(endOfPaginationReached)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, CatsDto>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { remoteKeysDao.getRemoteKey(it) }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CatsDto>): RemoteKeys? {
        return state.pages.firstOrNull()?.data?.firstOrNull()
            ?.let { remoteKeysDao.getRemoteKey(it.id) }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CatsDto>): RemoteKeys? {
        return state.pages.lastOrNull()
            ?.data?.lastOrNull()
            ?.let { remoteKeysDao.getRemoteKey(it.id) }
    }


    private suspend fun clearDBIfRefresh(loadType: LoadType, breedId: String?) {
        if (loadType == LoadType.REFRESH && breedId.isNullOrBlank()) {
            catsDao.clear()
            remoteKeysDao.clear()
        }
    }
}