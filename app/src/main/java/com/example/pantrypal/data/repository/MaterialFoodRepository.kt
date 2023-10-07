package com.example.pantrypal.data.repository

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.pantrypal.data.apihelper.ApiService
import com.example.pantrypal.data.paging.MaterialPagingSource
import com.example.pantrypal.data.response.AllMaterialResponse
import com.example.pantrypal.data.response.MaterialItem
import com.example.pantrypal.data.response.MaterialRequest
import com.example.pantrypal.data.response.MaterialResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MaterialFoodRepository private constructor(
    private val apiService: ApiService,
    private val context: Context
) {


    fun getAllMaterial(): LiveData<PagingData<AllMaterialResponse.ListMaterial>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                MaterialPagingSource(apiService)
            }
        ).liveData
    }

    fun getMaterialWithPrice(Harga: Int): LiveData<com.example.pantrypal.data.utils.Result<List<MaterialItem>>> {
        val materialLiveData =
            MutableLiveData<com.example.pantrypal.data.utils.Result<List<MaterialItem>>>()
        materialLiveData.value = com.example.pantrypal.data.utils.Result.Loading

        val request = MaterialRequest(Harga)
        apiService.searchMaterialWithPrice(request).enqueue(object : Callback<MaterialResponse> {
            override fun onResponse(
                call: Call<MaterialResponse>,
                response: Response<MaterialResponse>
            ) {
                if (response.isSuccessful) {
                    val material = response.body()?.material ?: emptyList()
                    materialLiveData.value =
                        com.example.pantrypal.data.utils.Result.Success(material)
                } else {
                    materialLiveData.value =
                        com.example.pantrypal.data.utils.Result.Error("Gagal mendapatkan data bahan")
                    Log.e(TAG, "Failed: Response Unsuccessful - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MaterialResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                materialLiveData.value =
                    com.example.pantrypal.data.utils.Result.Error("Terjadi kesalahan")
            }
        })
        return materialLiveData
    }

    companion object {
        @Volatile
        private var INSTANCE: MaterialFoodRepository? = null

        fun getInstance(apiService: ApiService, context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MaterialFoodRepository(apiService, context)
            }.also { INSTANCE = it }

        private const val TAG = "MaterialRepository"
    }
}