package com.tresbrosdevs.debabousoapp.activities

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tresbrosdevs.debabousoapp.R

class MainActivity : AppCompatActivity() {

    var TAG = "MainActivity"

    var buttonRegister: Button? = null//NULL SAFETY
    var editTextEmail: EditText? = null
    var editTextPassword: EditText? = null
    var buttonLogin: Button? = null



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
    }

    private fun login () {
        /*
        val email = editTextEmail?.text.toString()
        val password = editTextPassword?.text.toString()

        if (isValidForm(email, password)){
            Log.d(TAG, "Formulario correcto. Email: ${email}, Password: ${password}")
                }
        else{
            Log.d(TAG, "Formulario incorrecto.")
        }
         */
            var i = Intent(this, HomeActivity::class.java)
            startActivity(i)
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