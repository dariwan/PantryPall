package com.example.pantrypal.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pantrypal.R
import com.example.pantrypal.view.main.material.FoodMaterialFragment
import com.example.pantrypal.view.main.recipe.FoodRecipeFragment

class SectionsPagerAdapter(private val mCtx: Context, fm: FragmentManager, data: Bundle) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    private val TAB_TITLES = intArrayOf(
        R.string.food_material,
        R.string.food_recipe
    )

    private var fragmentBundle: Bundle

    init {
        fragmentBundle = data
    }

    @SuppressLint("ResourceType")
    @StringRes
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FoodMaterialFragment()
            1 -> fragment = FoodRecipeFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mCtx.resources.getString(TAB_TITLES[position])
    }

}