package com.example.pantrypal.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.pantrypal.data.apihelper.ApiService
import com.example.pantrypal.data.paging.MaterialPagingSource
import com.example.pantrypal.data.response.AllMaterialResponse
import java.math.MathContext

class MaterialFoodRepository private constructor(
    private val apiService: ApiService,
    private val context: Context
) {

    fun getAllMaterial(): LiveData<PagingData<AllMaterialResponse.ListMaterial>>{
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                MaterialPagingSource(apiService, context)
            }
        ).liveData
    }

    companion object{
        @Volatile
        private var INSTANCE: MaterialFoodRepository? = null

        fun getInstance(apiService: ApiService, context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: MaterialFoodRepository(apiService, context)
            }.also { INSTANCE = it }
    }
}