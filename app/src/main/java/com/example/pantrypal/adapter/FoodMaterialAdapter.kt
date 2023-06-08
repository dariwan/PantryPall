package com.example.pantrypal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pantrypal.data.response.AllMaterialResponse
import com.example.pantrypal.databinding.ListFoodMaterialBinding

class FoodMaterialAdapter:
    PagingDataAdapter<AllMaterialResponse.ListMaterial, FoodMaterialAdapter.MaterialViewHolder>(DIFF_CALLBACK)
{
    class MaterialViewHolder(val binding: ListFoodMaterialBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(material: AllMaterialResponse.ListMaterial){
            binding.tvMaterial.text = material.Nama
            binding.tvPrice.text = "Rp. ${material.Harga.toString()}"
            Glide.with(itemView.context)
                .load(material.image)
                .into(binding.imgFoodMaterial)
        }

    }

    override fun onBindViewHolder(holder: FoodMaterialAdapter.MaterialViewHolder, position: Int) {
        val material = getItem(position)
        if (material != null){
            holder.bind(material)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodMaterialAdapter.MaterialViewHolder {
        val binding = ListFoodMaterialBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MaterialViewHolder(binding)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AllMaterialResponse.ListMaterial>(){
            override fun areItemsTheSame(
                oldItem: AllMaterialResponse.ListMaterial,
                newItem: AllMaterialResponse.ListMaterial
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: AllMaterialResponse.ListMaterial,
                newItem: AllMaterialResponse.ListMaterial
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}