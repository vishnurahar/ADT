package com.example.adt.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.adt.model.UserList
import com.example.adt.networking.EmployeeAPI
import com.example.adt.networking.Response
import java.lang.Exception

class EmployeeListRepository(
    private val employeeApi: EmployeeAPI
) {
    private val employeeListLiveData = MutableLiveData<Response<UserList>>()

    val employeeList : LiveData<Response<UserList>>
    get() = employeeListLiveData

    suspend fun getEmployeeList() {
        val result = employeeApi.getEmployeeList()
            try {
                if (result.body() != null) {
                    employeeListLiveData.postValue(Response.Success(result.body()))
                } else {
                    employeeListLiveData.postValue(Response.Error("API Error"))
                }
            }catch (e : Exception){
                employeeListLiveData.postValue(Response.Error(e.message.toString()))
            }
    }
}