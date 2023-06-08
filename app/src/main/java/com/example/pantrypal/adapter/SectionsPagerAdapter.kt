package com.example.pantrypal.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pantrypal.view.main.material.FoodMaterialFragment
import com.example.pantrypal.view.main.recipe.FoodRecipeFragment

class SectionsPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FoodMaterialFragment()
            1 -> fragment = FoodRecipeFragment()
        }
        return fragment as Fragment
    }

}