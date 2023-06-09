package com.example.pantrypal.view.main.material

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pantrypal.R
import com.example.pantrypal.adapter.FoodMaterialAdapter
import com.example.pantrypal.adapter.SearchPriceAdapter
import com.example.pantrypal.data.apihelper.ApiConfig
import com.example.pantrypal.data.apihelper.ApiService
import com.example.pantrypal.data.response.MaterialItem
import com.example.pantrypal.databinding.FragmentFoodMaterialBinding

class FoodMaterialFragment : Fragment() {

    private var _binding: FragmentFoodMaterialBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FoodMaterialViewModel by viewModels {
        FoodMaterialViewModel.FoodMaterialFactory(requireContext())
    }

    private val searchViewModel: SearchPriceViewModel by viewModels {
        SearchPriceViewModel.SearchFoodMaterialFactory(requireContext())
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inisialisasi binding
        _binding = FragmentFoodMaterialBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_material, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //all data
        setupAction()


        //search
        searchPrice()
    }

    private fun setupRecyclerView() {

    }

    private fun setupAction() {
        val adapter = FoodMaterialAdapter()
        binding.rvMaterialFood.adapter = adapter
        binding.rvMaterialFood.layoutManager = LinearLayoutManager(requireContext())
        viewModel.material.observe(requireActivity()){
            adapter.submitData(lifecycle, it)
        }
//        binding.rvMaterialFood.adapter = adapter.withLoadStateFooter(
//            footer = Loa
//        )
    }

    private fun searchPrice(){
        binding.btnSearchMaterial.setOnClickListener {
            val material = binding.edtNominal.text.toString()
            val vinalValue = material.toInt()
            if (material.isNotEmpty()){
                searchViewModel.sendPrice(vinalValue).observe(requireActivity()){
                    when (it) {
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
                            showMaterial(it.data)
                        }
                    }
                }
//                searchViewModel.material.observe(viewLifecycleOwner) { result ->
//                    when (result) {
//                        is com.example.pantrypal.data.utils.Result.Loading -> {}
//                        is com.example.pantrypal.data.utils.Result.Success -> showMaterial(result.data)
//                        is com.example.pantrypal.data.utils.Result.Error -> showError(result.error)
//                    }
//                }
            }

        }
    }

    private fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    private fun showMaterial(material: List<MaterialItem>) {
        val adapter = SearchPriceAdapter()
        binding.rvMaterialFood.adapter = adapter
        binding.rvMaterialFood.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(material)

    }

    private fun showLoading() {


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}