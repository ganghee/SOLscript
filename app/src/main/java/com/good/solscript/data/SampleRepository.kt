package com.good.solscript.data

import com.good.solscript.data.remote.SampleRemoteDataSource
import io.reactivex.Single
import retrofit2.Call

class SampleRepository {
    fun getFakeDatas(): Single<List<SampleData>> =
        SampleRemoteDataSource.getFakeData()

    fun getSampleDates(): Call<List<SampleData>> =
        SampleRemoteDataSource.sampleRetrofit.getSample()
}