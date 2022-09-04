package com.example.adt.networking

import com.example.adt.model.UserList
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeAPI {

    @GET("v1/61cf7d91-a7f8-405e-b505-67926b759d78")
    suspend fun getEmployeeList() : Response<UserList>
}