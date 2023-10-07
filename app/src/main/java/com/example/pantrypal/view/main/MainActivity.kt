package com.example.pantrypal.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pantrypal.adapter.SectionsPagerAdapter
import com.example.pantrypal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupViewFragment()
    }

    private fun setupViewFragment() {
        val bundle = Bundle()
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

}