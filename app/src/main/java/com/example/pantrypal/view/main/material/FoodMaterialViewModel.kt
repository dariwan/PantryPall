package com.example.pantrypal.view.main.material

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pantrypal.data.di.Injection
import com.example.pantrypal.data.repository.MaterialFoodRepository
import com.example.pantrypal.data.response.AllMaterialResponse
import okhttp3.RequestBody

class FoodMaterialViewModel(materialFoodRepository: MaterialFoodRepository, context: Context): ViewModel() {
    val material : LiveData<PagingData<AllMaterialResponse.ListMaterial>> = materialFoodRepository.getAllMaterial().cachedIn(viewModelScope)

    class FoodMaterialFactory(private val context: Context): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FoodMaterialViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return FoodMaterialViewModel(Injection.provideRepository(context), context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}