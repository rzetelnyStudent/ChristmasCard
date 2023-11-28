package com.michalkapuscinski.christmascard

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.michalkapuscinski.christmascard.databinding.ActivityGeneratedCardBinding

class GeneratedCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGeneratedCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGeneratedCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameTextView.text = intent.getStringExtra("name")
        val uri: Uri? = intent.getParcelableExtra("uri")
        binding.profilePhotoView.setImageURI(uri)
        binding.giftListTextView.text = intent.getStringExtra("gift_list")
    }
}