package com.example.pantrypal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pantrypal.data.response.MaterialItem
import com.example.pantrypal.databinding.ListFoodMaterialBinding

class SearchPriceAdapter:
    RecyclerView.Adapter<SearchPriceAdapter.MaterialViewHolder>() {

    private val material: MutableList<MaterialItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListFoodMaterialBinding.inflate(inflater, parent, false)
        return MaterialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        val materials = material[position]
        holder.bind(materials)
    }

    override fun getItemCount(): Int {
        return material.size
    }

    fun submitList(newMaterial: List<MaterialItem>){
        material.clear()
        material.addAll(newMaterial)
        notifyDataSetChanged()
    }

    inner class MaterialViewHolder(private val binding: ListFoodMaterialBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(material: MaterialItem) {
            binding.tvMaterial.text = material.Nama
            binding.tvPrice.text = "Rp. ${material.Harga.toString()}"
            Glide.with(itemView.context)
                .load(material.image)
                .into(binding.imgFoodMaterial)
        }
    }
}