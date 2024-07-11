package com.tresbrosdevs.debabousoapp.providers

import com.tresbrosdevs.debabousoapp.api.ApiRoutes
import com.tresbrosdevs.debabousoapp.models.ResponseHttp
import com.tresbrosdevs.debabousoapp.models.User
import retrofit2.Call
import com.tresbrosdevs.debabousoapp.routes.UsersRoutes

class UsersProvider {

    private var usersRoutes: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRoutes()
    }

    fun register(user: User): Call<ResponseHttp>? {
        return usersRoutes?.register(user)
    }

    fun login(email: String, password: String): Call<ResponseHttp>? {
        return usersRoutes?.login(email, password)
    }
}