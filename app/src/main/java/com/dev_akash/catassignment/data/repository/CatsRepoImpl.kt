package com.dev_akash.catassignment.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.withTransaction
import com.dev_akash.catassignment.data.local.CatAppDB
import com.dev_akash.catassignment.data.paging.CatsPagingRemoteMediator
import com.dev_akash.catassignment.data.remote.CatApi
import com.dev_akash.catassignment.repository.CatsRepo
import com.dev_akash.catassignment.utils.network_utils.Resource
import com.dev_akash.catassignment.utils.network_utils.validate
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class CatsRepoImpl @Inject constructor(
    private val api: CatApi,
    private val catAppDB: CatAppDB
) : CatsRepo {

    override fun getCatList(query: String?) = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 35),
        remoteMediator = CatsPagingRemoteMediator(catAppDB, api, query),
        pagingSourceFactory = { catAppDB.getCatsDao().getCatsList() },
    ).flow

    override fun getFilters() = catAppDB.getFiltersDao().getFilters()

    override suspend fun getBreedsList() {
        val res = api.getBreedsList().validate()
        when (res) {
            is Resource.Success -> {
                res.data?.let { breeds ->
                    catAppDB.withTransaction {
                        val dao = catAppDB.getFiltersDao()
                        dao.clear()
                        dao.saveFilters(breeds.map { it.toFilterDto() })
                    }
                }
            }

            else -> {}
        }
    }

}