package com.example.appmovil_complexivo_crespomateo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Configura Retrofit para realizar la solicitud a la API.
object RetrofitClient {
    private const val BASE_URL = "https://satisfied-expression-production.up.railway.app/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}