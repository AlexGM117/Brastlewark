package com.alejandro.hob.data.remote

import com.google.gson.annotations.SerializedName

data class Brastlewark(val id: Int,
                       val name: String,
                       val thumbnail: String,
                       val age: Int,
                       val weight: Double,
                       val height: Double,
                       @SerializedName("hair_color") val hairColor: String,
                       val professions: List<String>,
                       val friends: List<String>)