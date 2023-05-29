package com.dev_akash.catassignment.feature_catshow.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.dev_akash.catassignment.feature_catshow.data.local.CatAppDB
import com.dev_akash.catassignment.feature_catshow.data.paging.CatsPagingRemoteMediator
import com.dev_akash.catassignment.feature_catshow.data.paging.CatsPagingSource
import com.dev_akash.catassignment.feature_catshow.data.remote.CatApi
import com.dev_akash.catassignment.feature_catshow.repository.CatsRepo
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class CatsRepoImpl @Inject constructor(
    private val api: CatApi,
    private val catAppDB: CatAppDB
) : CatsRepo {

    override fun getCatList() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 50),
        remoteMediator = CatsPagingRemoteMediator(catAppDB,api),
        pagingSourceFactory = { catAppDB.getCatsDao().getCatsList() }
    ).liveData


}