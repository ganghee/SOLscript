package com.good.solscript.data


import com.google.gson.annotations.SerializedName

data class ChatData(
    @SerializedName("contents")
    val contents: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("me")
    val me: Boolean
)