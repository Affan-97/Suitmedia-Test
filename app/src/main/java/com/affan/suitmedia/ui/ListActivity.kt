package com.affan.suitmedia.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.affan.suitmedia.adapter.ListAdapter
import com.affan.suitmedia.adapter.LoadingStateAdapter
import com.affan.suitmedia.databinding.ActivityListBinding
import com.affan.suitmedia.viewmodel.UserViewModel
import com.affan.suitmedia.viewmodel.ViewModelFactory

class ListActivity : AppCompatActivity() {

    private var _binding: ActivityListBinding? = null
    private val binding get() = _binding!!
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val mainViewModel: UserViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        swipeRefreshLayout = binding.swipeRefreshLayout
        binding.rvUser.layoutManager = LinearLayoutManager(this)

        fetchData()

        swipeRefreshLayout.setOnRefreshListener {
            fetchData()
        }
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun fetchData() {
        val adapter = ListAdapter(){
            val intent = Intent()

            intent.putExtra("username", "${it.firstName} ${it.lastName}")

            setResult(WelcomeActivity.RESULT_CODE, intent)
            finish()
        }

        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        mainViewModel.user.observe(this) {
            adapter.submitData(lifecycle, it)
            swipeRefreshLayout.isRefreshing = false

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