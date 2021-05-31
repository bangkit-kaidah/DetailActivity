package com.example.capstoneproject.item.serialized

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class SubjectSerialized(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String
) : Parcelable