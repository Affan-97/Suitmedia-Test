package com.affan.suitmedia.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.affan.suitmedia.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private var _binding: ActivityWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvName.text = intent.getStringExtra(MainActivity.EXTRA_NAME)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnChoose.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, ListActivity::class.java)
            launchList.launch(intent)
        }
    }

    private val launchList =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == RESULT_CODE) {

                binding.tvSelected.text = it.data?.getSerializableExtra("username").toString()
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

    companion object {
        const val RESULT_CODE = 200


    }
}