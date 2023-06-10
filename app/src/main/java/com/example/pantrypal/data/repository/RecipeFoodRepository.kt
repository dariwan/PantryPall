package com.example.pantrypal.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pantrypal.data.apihelper.ApiService
import com.example.pantrypal.data.response.*
import com.example.pantrypal.data.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeFoodRepository private constructor(
    private val apiService: ApiService,
    private val context: Context) {

    fun getRecipeWithCategory(category: String): LiveData<Result<List<RecipeItem>>> {
        val recipeLiveData = MutableLiveData<Result<List<RecipeItem>>>()
        recipeLiveData.value = com.example.pantrypal.data.utils.Result.Loading

        val request = CategoryRequest(category)
        apiService.searchRecipeWithCategory(request).enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                if (response.isSuccessful){
                    val material = response.body()?.recipe ?: emptyList()
                    recipeLiveData.value = com.example.pantrypal.data.utils.Result.Success(material)
                }else{
                    recipeLiveData.value = com.example.pantrypal.data.utils.Result.Error("Gagal mendapatkan data resep")
                    Log.e(RecipeFoodRepository.TAG, "Failed: Response Unsuccessful - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                Log.e(RecipeFoodRepository.TAG, "onFailure: ${t.message}")
                recipeLiveData.value = com.example.pantrypal.data.utils.Result.Error("Terjadi kesalahan")
            }

        })
        return recipeLiveData
    }

    companion object{
        @Volatile
        private var INSTANCE: RecipeFoodRepository? = null

        fun getInstance(apiService: ApiService, context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: RecipeFoodRepository(apiService, context)
            }.also { INSTANCE = it }

        private const val TAG = "RecipeRepository"
    }
}