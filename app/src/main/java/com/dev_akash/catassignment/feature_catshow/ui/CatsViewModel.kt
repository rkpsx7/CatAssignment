package com.dev_akash.catassignment.feature_catshow.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.dev_akash.catassignment.feature_catshow.data.repository.CatsRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val repo: CatsRepoImpl
) : ViewModel() {

    val catsList = repo.getCatList().cachedIn(viewModelScope)

//
//    fun getCatsList() {
//        ioJob {
//            catListUseCase()
//        }
//    }

}