package com.yaritzama.marvelapp.data.models.comicsresponse


import com.google.gson.annotations.SerializedName

data class DataComic(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<ResultComic>?,
    @SerializedName("total")
    val total: Int?
)