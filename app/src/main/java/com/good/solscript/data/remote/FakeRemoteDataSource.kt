package com.good.solscript.data.remote

import com.good.solscript.data.FakeResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object FakeRemoteDataSource {
    private val retrofit: FakeService = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(FakeService::class.java)

    fun getFakeData(): Single<List<FakeResponse>> =
        retrofit.getFakeDatas()
            .subscribeOn(Schedulers.io())
}
