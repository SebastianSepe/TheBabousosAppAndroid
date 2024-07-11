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
import com.tresbrosdevs.debabousoapp.R
import com.tresbrosdevs.debabousoapp.models.ResponseHttp
import com.tresbrosdevs.debabousoapp.models.User
import com.tresbrosdevs.debabousoapp.providers.UsersProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    val TAG = "RegisterActivity"

    var buttonGoToLogin: Button? = null //NULL SAFETY
    var editTextName: EditText? = null
    var editTextLastName: EditText? = null
    var editTextEmail: EditText? = null
    var editTextPhone: EditText? = null
    var editTextPassword: EditText? = null
    var editTextConfirmPassword: EditText? = null
    var buttonRegister: Button? = null

    var usersProvider = UsersProvider()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonGoToLogin = findViewById(R.id.btn_go_to_login)
        editTextName = findViewById(R.id.edittext_name)
        editTextLastName = findViewById(R.id.edittext_lastname)
        editTextEmail = findViewById(R.id.edittext_email)
        editTextPhone = findViewById(R.id.edittext_phone)
        editTextPassword = findViewById(R.id.edittext_password)
        editTextConfirmPassword = findViewById(R.id.edittext_confirmpassword)
        buttonRegister = findViewById(R.id.btn_register)


        buttonGoToLogin?.setOnClickListener { goToLogIn() }
        buttonRegister?.setOnClickListener{( register() )}

    }

    private fun register (){
        val name = editTextName?.text.toString()
        val lastname = editTextLastName?.text.toString()
        val email = editTextEmail?.text.toString()
        val phone = editTextPhone?.text.toString()
        val password = editTextPassword?.text.toString()
        val confirmpassword = editTextConfirmPassword?.text.toString()

        if (isValidForm(name,lastname,email,phone,password,confirmpassword)){

            val user = User(
                name = name,
                lastname = lastname,
                email = email,
                phone = phone,
                password = password
            )

            usersProvider.register(user)?.enqueue(object: Callback<ResponseHttp> {

                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
                    // Get the message from the response body, or use a default message if null
                    val message = response.body()?.message ?: "Operation completed"
                    Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_LONG).show()

                    // Log the response and response body for debugging purposes
                    Log.d(TAG, "Response: $response")
                    Log.d(TAG, "Response Body: ${response.body()}")
                }


                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "An error ocurred: ${t.message}")
                    Toast.makeText(this@RegisterActivity, "An error ocurred: ${t.message}", Toast.LENGTH_LONG).show()
                }

            })
        }
        else{
            Log.d(TAG, "Incorrect form. Please check the fields.")
        }
    }

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun isValidForm(
        name: String,
        lastname: String,
        email: String,
        phone: String,
        password: String,
        confimpassword: String

    ): Boolean {
        if (name.isBlank()) {
            return false
        }

        if (lastname.isBlank()) {
            return false
        }

        if (email.isBlank()) {
            return false
        }

        if (phone.isBlank()) {
            return false
        }

        if(password.isBlank()){
            return false
        }

        if (confimpassword.isBlank()) {
            return false
        }

        if (!email.isEmailValid()){
            return false
        }

        if(password != confimpassword){
            return false
        }

        return true
    }

    private fun goToLogIn(){
        val i = Intent(this, MainActivity::class.java )
        startActivity(i)
    }
}