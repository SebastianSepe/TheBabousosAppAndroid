package providers

import api.ApiRoutes
import models.ResponseHttp
import models.User
import retrofit2.Call
import routes.UsersRoutes

class UsersProvider {

    private var usersRoutes: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRoutes()
    }

    fun register(user: User): Call<ResponseHttp>? {
        return usersRoutes?.register(user)
    }
}