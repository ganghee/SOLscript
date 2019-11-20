package com.good.solscript.data.remote

import com.good.solscript.data.SampleData
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object SampleRemoteDataSource{
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    val sampleRetrofit : NetworkService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkService::class.java)

    //val service : NetworkService = sampleRetrofit.create(NetworkService::class.java)

    private val fakeRetrofit: NetworkService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NetworkService::class.java)

    fun getFakeData(): Single<List<SampleData>> =
        fakeRetrofit.getFakeDatas()
            .subscribeOn(Schedulers.io())
}