package com.example.pantrypal.data.response

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("data")
    val recipe: List<RecipeItem>
)

data class RecipeItem(
    @SerializedName("title")
    val title: String,

    @SerializedName("bahan")
    val bahan: String,

    @SerializedName("loves")
    val loves: Int,

    @SerializedName("step")
    val step: String,

    @SerializedName("kategori")
    val kategori: String,

    @SerializedName("deskripsi")
    val deskripsi: String,

    @SerializedName("image")
    val image: String,

)