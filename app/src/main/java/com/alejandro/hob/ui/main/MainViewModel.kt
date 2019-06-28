package com.alejandro.hob.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alejandro.hob.data.remote.Brastlewark
import com.alejandro.hob.ui.ApiRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val repository = ApiRepository()

    val data = MutableLiveData<MutableList<Brastlewark>>()

    fun getData() {
        scope.launch {
            val resultData = repository.getDataRepository()
            data.postValue(resultData)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}