package com.example.pantrypal.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class AllRecipeResponse (

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: List<ListRecipe>
){
    @Parcelize
    data class ListRecipe(

        @SerializedName("title")
        val title: String,

        @SerializedName("bahan")
        val bahan: String,

        @SerializedName("loves")
        val loves: Int,

    ): Parcelable
}