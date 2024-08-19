package com.example.appmovil_complexivo_crespomateo.network


import com.example.appmovil_complexivo_crespomateo.model.Producto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String, val role: String,val expiresIn:Int)
//Define la interfaz ApiService para las solicitudes HTTP.
interface ApiService {
    @GET("api/productos")
    fun getProducts(): Call<List<Producto>>

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}