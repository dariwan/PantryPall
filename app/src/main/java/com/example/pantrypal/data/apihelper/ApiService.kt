package com.example.pantrypal.data.apihelper

import com.example.pantrypal.data.response.*
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @GET("Dashboard/bahan/all")
    suspend fun getAllMaterial(
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
    ): AllMaterialResponse


    @POST("Dashboard/bahan")
    fun searchMaterialWithPrice(
       @Body request: MaterialRequest,
    ): Call<MaterialResponse>

    @POST("Dashboard/resep/category")
    fun searchRecipeWithCategory(
        @Body request: CategoryRequest,
    ): Call<RecipeResponse>

}