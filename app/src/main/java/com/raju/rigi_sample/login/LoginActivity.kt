package com.raju.rigi_sample.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.raju.rigi_sample.application.RigiApp
import com.raju.rigi_sample.chat.MainActivity
import com.raju.rigi_sample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private var name = ""
    private var password = ""
    private var isNameFilled = false
    private var isPasswordFilled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initActions()

    }

    private fun initViews() {
        binding.tilUserName.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                name = s.toString().trim()

                if (name.length >= 4) {

                } else {
                    showMessage("Please enter at least 4 characters for name.")
                }

                isNameFilled = name.length >= 3

            }
        })

        binding.tilPassword.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                password = s.toString().trim()

                if (password.length >= 4) {

                } else {
                    showMessage("Please enter at least 4 characters for password.")
                }
                isPasswordFilled = password.length >= 3
            }
        })
    }

    private fun initActions() {
        binding.btnLogin.setOnClickListener {
            if (!isNameFilled) {
                showMessage("Enter name")
            } else if (!isPasswordFilled) {
                showMessage("Enter password")
            }else if (isNameFilled && isPasswordFilled){
                if (name =="Rigi" && password == "Rigi"){
                    val app: RigiApp = application as RigiApp
                    val pref = app.getPref()
                    pref?.saveIsLogin(true)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    showMessage("Enter valid credential!")
                }
            }else{
                showMessage("Enter valid credential!")
            }
        }
    }

    fun showMessage(message: String) {
        val snack = Snackbar.make(binding.rlContainer, message, Snackbar.LENGTH_LONG)
        snack.setTextColor(Color.parseColor("#ffffff"))
        snack.show()
    }

}