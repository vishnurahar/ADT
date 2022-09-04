package com.example.adt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adt.model.UserList
import com.example.adt.networking.Response
import com.example.adt.repository.EmployeeListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: EmployeeListRepository
    ) : ViewModel() {

        init {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getEmployeeList()
            }
        }

    val employeeList: LiveData<Response<UserList>>
    get() = repository.employeeList
}