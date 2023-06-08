package com.example.pantrypal.view.main.material

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pantrypal.R
import com.example.pantrypal.adapter.FoodMaterialAdapter
import com.example.pantrypal.databinding.FragmentFoodMaterialBinding

class FoodMaterialFragment : Fragment() {

    private var _binding: FragmentFoodMaterialBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FoodMaterialViewModel by viewModels {
        FoodMaterialViewModel.FoodMaterialFactory(requireContext())
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
        setupAction()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}