package com.good.solscript.data.remote

import com.good.solscript.data.FakeResponse
import io.reactivex.Single
import retrofit2.http.GET

interface FakeService{
    @GET("/posts")
    fun getFakeDatas(): Single<List<FakeResponse>>
}