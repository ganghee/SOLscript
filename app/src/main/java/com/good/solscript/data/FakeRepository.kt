package com.good.solscript.data

import com.good.solscript.data.remote.FakeRemoteDataSource
import io.reactivex.Single

class FakeRepository {
    fun getFakeDatas(): Single<List<FakeResponse>> =
        FakeRemoteDataSource.getFakeData()

}