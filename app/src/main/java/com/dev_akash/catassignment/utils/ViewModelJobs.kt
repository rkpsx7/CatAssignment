package com.dev_akash.catassignment.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async

val io: CoroutineDispatcher = Dispatchers.IO


fun ViewModel.ioJob(block: suspend CoroutineScope.() -> Unit): Job =
    viewModelScope.async(io) {
        block()
    }