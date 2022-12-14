package com.yaritzama.marvelapp.data.models.comicsresponse


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price")
    val price: Double?,
    @SerializedName("type")
    val type: String?
)