package routes

import models.ResponseHttp
import models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersRoutes {

    @POST("api/users/register")
    fun register(@Body user: User): Call<ResponseHttp>

}