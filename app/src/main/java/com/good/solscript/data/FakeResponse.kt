package com.good.solscript.data

import com.google.gson.annotations.SerializedName

data class FakeResponse(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)