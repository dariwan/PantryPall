package com.example.pantrypal.data.di

import android.content.Context
import com.example.pantrypal.data.apihelper.ApiConfig
import com.example.pantrypal.data.repository.MaterialFoodRepository
import com.example.pantrypal.data.repository.RecipeFoodRepository

object Injection {
    fun provideRepository(context: Context): MaterialFoodRepository{
        val apiService = ApiConfig.getApiService()
        return MaterialFoodRepository.getInstance(apiService, context)

    }

    fun provideRecipeRepository(context: Context):RecipeFoodRepository{
        val apiService = ApiConfig.getApiService()
        return RecipeFoodRepository.getInstance(apiService, context)
    }
}