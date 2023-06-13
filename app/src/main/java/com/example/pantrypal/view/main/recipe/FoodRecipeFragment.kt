package com.example.pantrypal.view.main.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pantrypal.R
import com.example.pantrypal.adapter.SearchCategoryAdapter
import com.example.pantrypal.adapter.SearchLoveAdapter
import com.example.pantrypal.data.response.RecipeItem
import com.example.pantrypal.databinding.FragmentFoodMaterialBinding
import com.example.pantrypal.databinding.FragmentFoodRecipeBinding
import com.google.android.material.chip.Chip


class FoodRecipeFragment : Fragment() {
    private var _binding : FragmentFoodRecipeBinding? = null
    private val binding get() = _binding!!

    private val searchCategoryViewModel: SearchCategoryViewModel by viewModels {
        SearchCategoryViewModel.SearchFoodRecipeFactory(requireContext())
    }

    private val searchLoveViewModel: SearchLovesViewModel by viewModels {
        SearchLovesViewModel.SearchLovesFoodRecipeFactory(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inisialisasi binding
        _binding = FragmentFoodRecipeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_recipe, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchCategory()
        setupChip()
    }

    private fun searchCategory() {
        binding.btnSearchCategory.setOnClickListener {
            val category = binding.edtCategory.text.toString()
            if (category.isNotEmpty()){
                searchCategoryViewModel.sendCategory(category).observe(requireActivity()){
                    when(it){
                        is com.example.pantrypal.data.utils.Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is com.example.pantrypal.data.utils.Result.Error -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            val error = it.error
                            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        }
                        is com.example.pantrypal.data.utils.Result.Success -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            binding.chipGroup.visibility = View.VISIBLE

                            if (category == "Terjangkau" || category == "terjangkau" || category == "Murah" || category == "murah" || category == "Mahal" || category == "mahal"){
                                showMaterial(it.data)
                            }else{
                                Toast.makeText(requireContext(), "Input yang anda masukkan salah", Toast.LENGTH_SHORT).show()
                                binding.rvRecipeFood.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showMaterial(category: List<RecipeItem>) {
        val adapter = SearchCategoryAdapter()
        binding.rvRecipeFood.adapter = adapter
        binding.rvRecipeFood.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(category)
    }

    private fun searchLove(query: Int){
        val love = query
        if (love != null){
            searchLoveViewModel.sendLove(love).observe(requireActivity()){
                when(it){
                    is com.example.pantrypal.data.utils.Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is com.example.pantrypal.data.utils.Result.Error -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        val error = it.error
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                    is com.example.pantrypal.data.utils.Result.Success -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.chipGroup.visibility = View.VISIBLE
                        showLove(it.data)
                    }
                }
            }
        }
    }

    private fun showLove(Loves: List<RecipeItem>) {
        val adapter = SearchLoveAdapter()
        binding.rvRecipeFood.adapter = adapter
        binding.rvRecipeFood.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(Loves)

    }

    private fun setupChip(){
        binding.chipKurangDari5.setOnClickListener {
            searchLove(5)
        }

        binding.chip2KurangDari10.setOnClickListener {
            searchLove(10)
        }

        binding.chipKurangDari20.setOnClickListener {
            searchLove(20)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}