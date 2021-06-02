package com.example.capstoneproject.retrofit

import com.example.capstoneproject.datadetail.DetailSerialized
import com.example.capstoneproject.datajdhin.JdhinSerialized
import com.example.capstoneproject.dataregulation.SpecialSerialized
import com.example.capstoneproject.datasubject.SubjectSerialized
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DataApi {
    @GET("api/v1/subjects")
    fun getDataSubject(): Call<ArrayList<SubjectSerialized>>

    @GET("api/v1/documents/{id}")
    fun getDetail(@Path("id") dataID: Int): Call<List<DetailSerialized>>

    @GET("api/v1/documents")
    fun getDataSpecial(@Query("subject")dataID: Int): Call<SpecialSerialized>

    @GET("api/v1/sources")
    fun getDataJdhin(): Call<ArrayList<JdhinSerialized>>

    @GET("api/v1/documents")
    fun getRegulationJdhin(@Query("source")dataID: Int): Call<SpecialSerialized>

    @GET("api/v1/subjects")
    fun getSearch(@Query("search")dataInput: String): Call<ArrayList<SubjectSerialized>>

    @GET("api/v1/sources")
    fun getSearchJdhin(@Query("search")dataInput: String): Call<ArrayList<JdhinSerialized>>

    //fun getDataSpecial(@Query("subject")dataID: Int): Call<SpecialSerialized>
    //masukkan data di SubjectSerialized seperti title dan masa berlaku
    //pastikan dulu bahwa getDataSubject dapat berjalan semestinya


}