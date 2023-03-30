package com.example.devstationtest.utils

class ApiConstants {
    companion object{
        private const val PROTOCOL = "https://"
        private const val DOMAIN = "randomuser.me/"
        const val PATH = "api/"
        const val BASE_URL = "$PROTOCOL$DOMAIN"
        const val CONNECTION_TIME_OUT = 20L
        const val READ_TIMEOUT = 20L
    }
}