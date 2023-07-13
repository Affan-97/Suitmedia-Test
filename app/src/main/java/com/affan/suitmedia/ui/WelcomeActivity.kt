package com.affan.suitmedia.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.affan.suitmedia.databinding.ActivityWelcomeBinding
import com.affan.suitmedia.utils.showToast

class WelcomeActivity : AppCompatActivity() {
    private var _binding: ActivityWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvName.text = intent.getStringExtra(MainActivity.EXTRA_NAME)
        binding.tvSelected.text = "Affan"

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnChoose.setOnClickListener {
            showToast(this,"Clicked")
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}