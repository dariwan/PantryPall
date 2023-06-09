package com.example.pantrypal.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.example.pantrypal.R
import com.example.pantrypal.adapter.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupViewFragment()
    }

    private fun setupViewFragment(){
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager){
            tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()


    }

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.food_material,
            R.string.food_recipe
        )
    }
}