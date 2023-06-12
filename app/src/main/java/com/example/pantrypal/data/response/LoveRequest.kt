package com.example.pantrypal.data.response

import com.google.gson.annotations.SerializedName

data class LoveRequest (
    @SerializedName("Loves")
    val Loves: Int,
)