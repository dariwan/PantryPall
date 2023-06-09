package com.example.pantrypal.data.response

import com.google.gson.annotations.SerializedName

data class MaterialRequest(
    @SerializedName("Harga")
    val Harga: Int,
)
