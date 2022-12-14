package com.yaritzama.marvelapp.data.models.character


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<ResultResponse>?,
    @SerializedName("total")
    val total: Int?
)