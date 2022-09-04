package com.example.adt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adt.ADTApplication
import com.example.adt.R
import com.example.adt.adapter.EmployeeListAdapter
import com.example.adt.databinding.ActivityMainBinding
import com.example.adt.networking.Response
import com.example.adt.viewmodel.MainViewModel
import com.example.adt.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    private val employeeListAdapter by lazy { EmployeeListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val repository = (application as ADTApplication).employeeListRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.employeeList.observe(this) { response ->
            when (response) {
                is Response.Loading -> {}
                is Response.Success -> {
                    response.data?.let {
                        employeeListAdapter.submitList(it.data)
                    }
                }
                is Response.Error -> {
                    Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.employeeListRv.apply {
            val verticalLayout = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            layoutManager = verticalLayout
            adapter = employeeListAdapter
        }
    }
}