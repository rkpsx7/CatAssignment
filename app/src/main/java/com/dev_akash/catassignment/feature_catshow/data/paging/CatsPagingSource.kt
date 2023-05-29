package com.dev_akash.catassignment.feature_catshow.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev_akash.catassignment.feature_catshow.data.remote.CatApi
import com.dev_akash.catassignment.feature_catshow.data.model.CatsDto
import com.dev_akash.catassignment.utils.network.validate

class CatsPagingSource(private val api: CatApi) : PagingSource<Int, CatsDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatsDto> {
        return try {
            val page = params.key ?: 1
            val response = api.getCatList(page).validate()
            LoadResult.Page(
                data = response.data ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.data.isNullOrEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, CatsDto>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
    /**
     * anchorPosition -> returns page no of recently accessed index(item)
     *
     * fn closestPageToPosition() -> returns closest page using anchorPosition
     */
}