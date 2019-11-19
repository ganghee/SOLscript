package com.good.solscript.data.remote

import com.good.solscript.data.SampleData
import retrofit2.Call
import retrofit2.http.GET

interface NetworkService{

    @GET("/posts")
    fun getSample(): Call<List<SampleData>>
}