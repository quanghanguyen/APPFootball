package com.example.appfootball.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.appfootball.R
import com.example.appfootball.databinding.ActivityLoginBinding
import com.shashank.sony.fancytoastlib.FancyToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailFocusListener()
        passwordFocusListener()

        binding.btnLogin.setOnClickListener{login()}
    }

    private fun login() {
        val validEmail = binding.emailContainer.helperText == null
        val validPassword = binding.passwordContainer.helperText == null

        if (validEmail && validPassword)
            loginSuccess()
        else
            loginFail()
    }

    private fun loginFail() {
        Toast.makeText(this, "Please check your Login Information again", Toast.LENGTH_SHORT).show()
    }

    private fun loginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        //FancyToast.makeText(this, "Login Success", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show()

        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
    }

    // Email

    private fun emailFocusListener() {
        binding.edtEmail.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {

        val emailText = binding.edtEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())

        {
            //binding.emailContainer.setErrorTextColor()
            return "!!! Invalid Email"
        }

        return null
    }

    // Password

    private fun passwordFocusListener() {
        binding.edtPassword.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {

        val passwordText = binding.edtPassword.text.toString()
        if (passwordText.length < 8)

        {
            //binding.emailContainer.setErrorTextColor()
            return "!!! Minimum 8 Character Password"
        }

        if (!passwordText.matches(".*[A-Z].*".toRegex()))

        {
            return "!!! Must contain 1 Upper-case Character"
        }

        if (!passwordText.matches(".*[a-z].*".toRegex()))

        {
            return "!!! Must contain 1 Lower-case Character"
        }

        if (!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))

        {
            return "!!! Must contain 1 Special Character (@#\$%^&+=)"
        }

        return null
    }

}