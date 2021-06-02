package com.example.capstoneproject.dataregulation

import com.google.gson.annotations.SerializedName

data class SpecialSerialized(
    @SerializedName("data")
    val data: ArrayList<DataSerialized>
)