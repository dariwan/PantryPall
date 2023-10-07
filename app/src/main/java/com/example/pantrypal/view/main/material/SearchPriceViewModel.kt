package com.example.pantrypal.view.main.material

import android.content.Context
import androidx.lifecycle.*
import com.example.pantrypal.data.di.Injection
import com.example.pantrypal.data.repository.MaterialFoodRepository

class SearchPriceViewModel(private val materialFoodRepository: MaterialFoodRepository): ViewModel() {

    fun sendPrice(Harga: Int) = materialFoodRepository.getMaterialWithPrice(Harga)

    class SearchFoodMaterialFactory(private val context: Context): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchPriceViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return SearchPriceViewModel(Injection.provideRepository(context)) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}