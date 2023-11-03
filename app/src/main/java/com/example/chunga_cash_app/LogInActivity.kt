package com.example.chunga_cash_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.chunga_cash_app.databinding.ActivityLoginBinding
import com.example.chunga_cash_app.databinding.ActivityUserConfirmationBinding

class LogInActivity : Activity () {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.logInBtn.setOnClickListener {
            val intent =  Intent(this@LogInActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}