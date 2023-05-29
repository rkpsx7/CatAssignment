package com.dev_akash.catassignment.feature_catshow.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import com.dev_akash.catassignment.feature_catshow.data.model.CatsDto

interface CatsRepo {
    fun getCatList(): LiveData<PagingData<CatsDto>>
}