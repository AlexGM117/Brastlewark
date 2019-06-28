package com.alejandro.hob.ui

import com.alejandro.hob.api.ApiClient
import com.alejandro.hob.data.manager.BaseRepository
import com.alejandro.hob.data.remote.Brastlewark

class ApiRepository : BaseRepository() {

    suspend fun getDataRepository() : MutableList<Brastlewark>? {
        val dataResponse = safeApiCall(
            call = {ApiClient.getInstance().getData().await()},
            errorMessage = "Error obteniendo datos")

        return dataResponse?.brastlewark!!.toMutableList()
    }
}