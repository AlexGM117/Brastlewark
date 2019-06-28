package com.alejandro.hob.data.remote

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("Brastlewark") val brastlewark: List<Brastlewark>
)