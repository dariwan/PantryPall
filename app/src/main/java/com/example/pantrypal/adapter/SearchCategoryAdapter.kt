package com.example.pantrypal.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.example.pantrypal.data.response.MaterialItem
import com.example.pantrypal.data.response.RecipeItem
import com.example.pantrypal.databinding.ListFoodRecipeBinding
import com.example.pantrypal.view.detail.DetailActivity

class SearchCategoryAdapter:
    RecyclerView.Adapter<SearchCategoryAdapter.CategoryViewHolder>(){

    private val category: MutableList<RecipeItem> = mutableListOf()

    inner class CategoryViewHolder(val binding: ListFoodRecipeBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(category: RecipeItem){
                binding.tvRecipe.text = category.title
                binding.heartTextView.text = category.loves.toString()
                binding.categoryTextView.text = category.kategori.toString()
            }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchCategoryAdapter.CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListFoodRecipeBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchCategoryAdapter.CategoryViewHolder, position: Int) {
        val categories = category[position]
        holder.bind(categories)

        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.NAME_EXTRA, categories?.title)
            intent.putExtra(DetailActivity.MATERIAL, categories?.bahan)
            intent.putExtra(DetailActivity.STEP, categories?.step)

            val optionsCompact: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    holder.itemView.context as Activity,
                    Pair(holder.binding.foodRecipe, "name"),
                    Pair(holder.binding.heartTextView, "love")
                )
            holder.itemView.context.startActivity(intent, optionsCompact.toBundle())
        }
    }

    override fun getItemCount(): Int {
        return category.size
    }

    fun submitList(newCategory: List<RecipeItem>){
        category.clear()
        category.addAll(newCategory)
        notifyDataSetChanged()
    }
}