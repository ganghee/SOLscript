package com.good.solscript.data


import com.google.gson.annotations.SerializedName

data class SelectedData(
    @SerializedName("app_name")
    val appName: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("img")
    val img : String
)