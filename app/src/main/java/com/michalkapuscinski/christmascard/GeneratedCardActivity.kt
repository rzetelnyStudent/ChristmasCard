package com.michalkapuscinski.christmascard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.michalkapuscinski.christmascard.databinding.ActivityGeneratedCardBinding

class GeneratedCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGeneratedCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGeneratedCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}