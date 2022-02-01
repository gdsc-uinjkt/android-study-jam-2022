package com.miko.movieapp.network

import com.miko.movieapp.utils.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    companion object {
        @Volatile
        private var INSTANCE: ApiService? = null

        fun getInstance(): ApiService =
            INSTANCE ?: synchronized(this) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Const.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val apiService = retrofit.create(ApiService::class.java)
                INSTANCE = apiService
                INSTANCE!!
            }
    }
}