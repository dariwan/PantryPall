package com.example.pantrypal.data.apihelper

import com.example.pantrypal.data.response.AllMaterialResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("Dashboard/bahan/all")
    suspend fun getAllMaterial(
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
    ): AllMaterialResponse

}