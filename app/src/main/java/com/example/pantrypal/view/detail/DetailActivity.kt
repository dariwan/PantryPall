package com.example.pantrypal.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pantrypal.R
import com.example.pantrypal.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        val name = intent.getStringExtra(NAME_EXTRA)
        val material = intent.getStringExtra(MATERIAL)
        val step = intent.getStringExtra(STEP)

        val cleanMaterial = material?.replace("--", "\n")
        val cleanStep = step?.replace("--","\n\n")?.replace("?", "")


        supportActionBar?.hide()
        binding.foodName.text = name
        binding.tvBahan.text = cleanMaterial
        binding.tvStep.text = cleanStep
    }


    companion object {
        const val NAME_EXTRA = "name_extra"
        const val DESCRIPTION_EXTRA = "desc_extra"
        const val IMAGE_URL_EXTRA = "img_extra"
        const val MATERIAL = "material_extra"
        const val STEP = "step_extra"
    }
}