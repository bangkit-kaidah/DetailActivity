package com.example.capstoneproject.item.serialized

import com.google.gson.annotations.SerializedName

data class DetailSerialized(
        @SerializedName("id")
        val id : Int,

        @SerializedName("judul_dokumen")
        val titleDocument : String,

        @SerializedName("nomor_peraturan")
        val numberRegulation : String,

        @SerializedName("tempat_penetapan")
        val placeDetermination : String,

        @SerializedName("tanggal_penetapan")
        val dateDetermination : String,

        @SerializedName("tanggal_pengundangan")
        val dateRelease : String,
)