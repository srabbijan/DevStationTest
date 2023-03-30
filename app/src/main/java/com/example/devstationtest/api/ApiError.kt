package com.example.devstationtest.api

class ApiError {
    fun returnResponseError(code: Int): String {
        var error = ""
        when (code) {
            400 -> error = "Bad Request"
            401 -> error = "Unauthorized"
            404 -> error = "Not Found"
            405 -> error = "Method Not Allowed"
            408 -> error = "Request Timeout"
            500 -> error = "Internal Server Error"
            502 -> error = "Bad Gateway"
        }
        return error
    }

}