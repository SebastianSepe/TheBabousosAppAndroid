package com.tresbrosdevs.debabousoapp.models

import com.google.gson.annotations.SerializedName


class User (
    @SerializedName("id") val id: String? = null,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("genre") val genre: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("password") val password: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("image") val image: String? = null,
    @SerializedName("birthday_date") val birthdayDate: String? = null,
    @SerializedName("session_token") val sessionToken: String? = null

){
    override fun toString(): String {
        return "User(id='$id', email='$email', name='$name', lastname='$lastname', genre='$genre', country='$country', city='$city', password='$password', phone='$phone', image='$image', birthdayDate='$birthdayDate', sessionToken='$sessionToken')"
    }
}

