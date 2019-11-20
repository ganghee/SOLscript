package com.good.solscript.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SampleRemoteDataSource{
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : NetworkService = retrofit.create(NetworkService::class.java)
}