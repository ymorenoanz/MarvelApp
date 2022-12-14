package com.yaritzama.marvelapp.data.models.comicsresponse


import com.google.gson.annotations.SerializedName

data class Variant(
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?
)