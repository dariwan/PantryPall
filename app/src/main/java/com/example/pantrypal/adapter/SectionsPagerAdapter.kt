package com.example.pantrypal.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pantrypal.view.main.FoodMaterialFragment
import com.example.pantrypal.view.main.FoodRecipeFragment
import com.example.pantrypal.view.main.MainActivity

class SectionsPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FoodRecipeFragment()
            1 -> fragment = FoodMaterialFragment()
        }
        return fragment as Fragment
    }

}