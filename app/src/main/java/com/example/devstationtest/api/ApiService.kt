package com.example.devstationtest.api

import com.example.devstationtest.model.Users
import com.example.devstationtest.utils.ApiConstants.Companion.PATH
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(PATH)
    suspend fun getUsersList()  : Response<Users>
}