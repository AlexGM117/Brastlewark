package com.alejandro.hob.api

import com.alejandro.hob.data.remote.DataResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("data.json")
    fun getData() : Deferred<Response<DataResponse>>
}

