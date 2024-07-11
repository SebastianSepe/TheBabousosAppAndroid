package com.tresbrosdevs.debabousoapp.routes

import com.tresbrosdevs.debabousoapp.models.ResponseHttp
import com.tresbrosdevs.debabousoapp.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UsersRoutes {

    @POST("api/users/register")
    fun register(@Body user: User): Call<ResponseHttp>

    @FormUrlEncoded
    @POST("api/users/login")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<ResponseHttp>




}