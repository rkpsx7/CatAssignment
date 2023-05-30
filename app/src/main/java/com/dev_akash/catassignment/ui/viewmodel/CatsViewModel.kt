package com.dev_akash.catassignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.filter
import com.dev_akash.catassignment.data.repository.CatsRepoImpl
import com.dev_akash.catassignment.utils.ioJob
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CatsViewModel @Inject constructor(
    private val repo: CatsRepoImpl
) : ViewModel() {
    val filters = repo.getFilters()

    private val breedId = MutableStateFlow<String?>(null)

    val catsList = breedId.flatMapLatest { query ->
        repo.getCatList(query).map {
            if (!breedId.value.isNullOrBlank()) {
                it.filter { it.breeds.firstOrNull()?.id == breedId.value }
            } else it
        }.flowOn(Dispatchers.IO)
    }


    fun prepareBreedFilterList() = ioJob {
        repo.getBreedsList()
    }

    fun setBreedId(searchQuery: String) = ioJob {
        breedId.emit(
            if (searchQuery.isBlank())
                null
            else
                filters.value?.find { it.breedName == searchQuery.trim() }?.id ?: return@ioJob
        )
    }

}