package com.example.adt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.adt.repository.EmployeeListRepository

class MainViewModelFactory(
    private val repository: EmployeeListRepository
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}