package com.example.capstoneproject.item.retrofit

import com.example.capstoneproject.item.serialized.DetailSerialized
import com.example.capstoneproject.item.serialized.PostSerialized
import com.example.capstoneproject.item.serialized.SpecialSerialized
import com.example.capstoneproject.item.serialized.SubjectSerialized
import retrofit2.Call
import retrofit2.http.GET

interface DataApi {
    @GET("api/v1/documents")
    fun getData(): Call<PostSerialized>

    @GET("api/v1/documents/1")
    fun getDetail(): Call<List<DetailSerialized>>

    @GET("api/v1/documents")
    fun getDataSpecial(): Call<SpecialSerialized>

    @GET("api/v1/subjects")
    fun getDataSubject(): Call<List<SubjectSerialized>>

}