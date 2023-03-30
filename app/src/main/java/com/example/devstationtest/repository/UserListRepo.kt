package com.example.devstationtest.repository

import com.example.devstationtest.api.ApiService
import com.example.devstationtest.model.Users
import javax.inject.Inject

class UserListRepo @Inject constructor(private val  apiService: ApiService) {

    suspend fun getUsers(): Users? {
        val response = apiService.getUsersList()
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!
        }
        else {
            null
        }


    }

}