package com.example.pantrypal.view.main.material

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.pantrypal.data.di.Injection
import com.example.pantrypal.data.repository.MaterialFoodRepository
import com.example.pantrypal.data.response.MaterialItem
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class SearchPriceViewModel(private val materialFoodRepository: MaterialFoodRepository): ViewModel() {

//    private val _material = MutableLiveData<com.example.pantrypal.data.utils.Result<List<MaterialItem>>>()
//    val material: LiveData<com.example.pantrypal.data.utils.Result<List<MaterialItem>>>
//    get() = _material
//
//    fun getMaterialbyPrice(Harga: Int){
//        _material.value = com.example.pantrypal.data.utils.Result.Loading
//        _material.postValue(materialFoodRepository.getMaterialWithPrice(Harga).value)
//    }

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