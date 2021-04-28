package com.kangdroid.k_labapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kangdroid.k_labapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)
    }
}