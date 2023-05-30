package com.dev_akash.catassignment.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.dev_akash.catassignment.data.model.CatsDto
import com.dev_akash.catassignment.data.model.FiltersDto
import kotlinx.coroutines.flow.Flow

interface CatsRepo {
    fun getCatList(query: String?): Flow<PagingData<CatsDto>>

    fun getFilters(): LiveData<List<FiltersDto>>

    suspend fun getBreedsList()
}