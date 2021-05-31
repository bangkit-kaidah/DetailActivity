package com.example.capstoneproject.item.serialized

import com.google.gson.annotations.SerializedName

data class DataSerialized(
    @SerializedName("judul_dokumen")
    val title: String,

    @SerializedName("nomor_peraturan")
    val numberRegulation: String,

    val status: DataId,

    val subject: DataSubject
)


data class DataId(
    val id: String,
    val name: String
)

data class DataSubject(
    val id: String,
    val name: String
)
