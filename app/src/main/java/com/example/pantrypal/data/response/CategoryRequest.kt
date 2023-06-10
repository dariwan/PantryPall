package com.example.pantrypal.data.response

import com.google.gson.annotations.SerializedName

data class CategoryRequest (
    @SerializedName("category")
    val category: String,
)