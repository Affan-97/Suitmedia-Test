package com.affan.suitmedia

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.affan.suitmedia.databinding.ActivityMainBinding
import com.affan.suitmedia.utils.palindromeChecker
import com.affan.suitmedia.utils.showToast

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val txtName = binding.edName
        val txtPalindrome = binding.edPalindrome
        val btnNext = binding.btnNext

        binding.btnCheck.setOnClickListener {
            if (txtPalindrome.text.toString().isEmpty()) {
                showToast(this, "Palindrome Field Is Empty")
            } else {

                val isPalindrome = palindromeChecker(txtPalindrome.text.toString())

                if (isPalindrome) {
                    showDialog(getString(R.string.ispalindrome))
                } else {
                    showDialog(getString(R.string.not_palindrome))
                }
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showDialog(text: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.palindrome_check))
        builder.setMessage(text)


        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }
}