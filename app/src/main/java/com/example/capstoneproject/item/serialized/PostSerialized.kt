package com.example.capstoneproject.item.serialized

import com.google.gson.annotations.SerializedName


data class PostSerialized(
    @SerializedName("current_page")
    val jumlahHalaman: Int,

    @SerializedName("first_page_url")
    val urlPertama: String,

    @SerializedName("path")
    val alur: String
)