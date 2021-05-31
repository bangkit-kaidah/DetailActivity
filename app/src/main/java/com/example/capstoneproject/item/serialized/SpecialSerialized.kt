package com.example.capstoneproject.item.serialized

import com.google.gson.annotations.SerializedName

data class SpecialSerialized(
    @SerializedName("data")
    val data: ArrayList<DataSerialized>
)