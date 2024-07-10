package api

import routes.UsersRoutes

class ApiRoutes {

    val API_URL = "http://10.0.2.2:3000/"
    val retrofit = RetrofitClient()

    fun getUsersRoutes(): UsersRoutes {
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }
}