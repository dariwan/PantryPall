package com.example.pantrypal.data.di

import android.content.Context
import com.example.pantrypal.data.apihelper.ApiConfig
import com.example.pantrypal.data.repository.MaterialFoodRepository

object Injection {
    fun provideRepository(context: Context): MaterialFoodRepository{
        val apiService = ApiConfig.getApiService()
        return MaterialFoodRepository.getInstance(apiService, context)
    }
}