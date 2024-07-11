package com.tresbrosdevs.debabousoapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.tresbrosdevs.debabousoapp.R
import com.tresbrosdevs.debabousoapp.activities.client.home.ClientHomeActivity
import com.tresbrosdevs.debabousoapp.models.ResponseHttp
import com.tresbrosdevs.debabousoapp.models.User
import com.tresbrosdevs.debabousoapp.providers.UsersProvider
import com.tresbrosdevs.debabousoapp.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var TAG = "MainActivity"

    var buttonRegister: Button? = null//NULL SAFETY
    var editTextEmail: EditText? = null
    var editTextPassword: EditText? = null
    var buttonLogin: Button? = null
    var usersProvider = UsersProvider()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        editTextEmail = findViewById(R.id.edittext_email)
        editTextPassword = findViewById(R.id.edittext_password)
        buttonLogin = findViewById(R.id.btn_login)
        buttonRegister = findViewById(R.id.btn_signup)
        buttonRegister?.setOnClickListener { goToRegister() }
        buttonLogin?.setOnClickListener{(login())}

        getUserFromSession()
    }

    private fun login () {
        val email = editTextEmail?.text.toString()
        val password = editTextPassword?.text.toString()
        //var i = Intent(this, HomeActivity::class.java)

        if (isValidForm(email, password)){

            usersProvider.login(email, password)?.enqueue(object: Callback<ResponseHttp> {
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {

                    Log.d(TAG, "Response: ${response.body()}")

                    if (response.isSuccessful){
                        Toast.makeText(this@MainActivity, response.body()?.message, Toast.LENGTH_LONG).show()
                        saveUserInSession(response.body()?.data.toString())
                        goToClientHome()

                    }
                    else{
                        Toast.makeText(this@MainActivity, "Incorrect user or password", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "An error ocurred ${t.message}")
                    Toast.makeText(this@MainActivity, "An error ocurred ${t.message}", Toast.LENGTH_LONG).show()
                }
            }

            )
                }
        else{
            Log.d(TAG, "Formulario incorrecto.")
        }
    }

    private fun goToClientHome(){
        val i = Intent(this, ClientHomeActivity::class.java)
        startActivity(i)
    }
    private fun saveUserInSession(data: String){
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user", user)
    }

    private fun getUserFromSession() {
        val sharedPref = SharedPref(this)
        val gson = Gson()

        if (!sharedPref.getData("user").isNullOrBlank()) {
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java)

            goToClientHome()
        }
    }

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun isValidForm(email: String, password: String): Boolean {
        if (email.isBlank()) {
            return false
        }

        if(password.isBlank()){
            return false
        }

        if (!email.isEmailValid()){
            return false
        }

        return true
    }


    private fun goToRegister(){
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }

}