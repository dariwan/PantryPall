package com.example.pantrypal.view.main.recipe

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pantrypal.data.di.Injection
import com.example.pantrypal.data.repository.RecipeFoodRepository
import com.example.pantrypal.view.main.material.SearchPriceViewModel

class SearchCategoryViewModel(private val recipeFoodRepository: RecipeFoodRepository): ViewModel() {
    fun sendCategory(category: String) = recipeFoodRepository.getRecipeWithCategory(category)

    class SearchFoodRecipeFactory(private val context: Context): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchCategoryViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return SearchCategoryViewModel(Injection.provideRecipeRepository(context)) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}