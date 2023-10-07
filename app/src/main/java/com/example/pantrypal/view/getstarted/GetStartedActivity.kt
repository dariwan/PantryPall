package com.example.pantrypal.view.getstarted

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pantrypal.databinding.ActivityGetStartedBinding
import com.example.pantrypal.view.main.MainActivity

class GetStartedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetStartedBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonGetStarted.setOnClickListener {
            isFirstTimeLaunch()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun isFirstTimeLaunch(){
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putBoolean(FIRST_TIME_LAUNCH, true)
        }.apply()
    }


    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFirstTimeLaunch = sharedPreferences.getBoolean(FIRST_TIME_LAUNCH, false)
        if (isFirstTimeLaunch){
            val intentToMain = Intent(this@GetStartedActivity, MainActivity::class.java)
            startActivity(intentToMain)
            finish()
        }
    }


    companion object{
        const val PREFS_NAME = "pantrypall_app_pref"
        const val FIRST_TIME_LAUNCH = "first_time_launch"
    }



}