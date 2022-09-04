package com.example.adt

import android.app.Application
import com.example.adt.networking.EmployeeAPI
import com.example.adt.networking.RetrofitInstance
import com.example.adt.repository.EmployeeListRepository

class ADTApplication : Application() {

    lateinit var employeeListRepository: EmployeeListRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val employeeApi = RetrofitInstance.getInstance().create(EmployeeAPI::class.java)
        employeeListRepository = EmployeeListRepository(employeeApi)
    }
}