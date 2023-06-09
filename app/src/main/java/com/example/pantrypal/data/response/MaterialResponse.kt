package com.example.pantrypal.data.response

import com.google.gson.annotations.SerializedName

data class MaterialResponse(
    @SerializedName("data")
    val material: List<MaterialItem>
)

data class MaterialItem(
    @SerializedName("id")
    val id: String,

    @SerializedName("Nama")
    val Nama: String,

    @SerializedName("Harga")
    val Harga: Int,

    @SerializedName("image")
    val image: String,
)
