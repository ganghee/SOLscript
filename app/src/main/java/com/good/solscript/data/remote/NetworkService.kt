package com.good.solscript.data.remote

import com.good.solscript.data.SampleData
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface NetworkService{

    @GET("/posts")
    fun getSample(): Call<List<SampleData>>

    @GET("/posts")
    fun getFakeDatas(): Single<List<SampleData>>
}