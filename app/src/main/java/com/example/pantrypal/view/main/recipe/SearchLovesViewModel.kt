package com.example.pantrypal.view.main.recipe

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pantrypal.data.di.Injection
import com.example.pantrypal.data.repository.RecipeFoodRepository

class SearchLovesViewModel(private val recipeFoodRepository: RecipeFoodRepository): ViewModel() {

    fun sendLove(Loves: Int) = recipeFoodRepository.getRecipeWithLoves(Loves)

    class SearchLovesFoodRecipeFactory(private val context: Context): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchLovesViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return SearchLovesViewModel(Injection.provideRecipeRepository(context)) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}