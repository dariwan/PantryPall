package com.example.pantrypal.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class AllMaterialResponse (

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: List<ListMaterial>
    ){
    @Parcelize
    data class ListMaterial(

        @SerializedName("id")
        val id: String,

        @SerializedName("Nama")
        val Nama: String,

        @SerializedName("Harga")
        val Harga: Int,

        @SerializedName("image")
        val image: String,

    ): Parcelable
}