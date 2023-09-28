package com.example.chunga_cash_app

import android.app.Activity
import android.os.Bundle
import com.example.chunga_cash_app.databinding.ActivityAdminSignupBinding
import android.content.Intent

class SignUpActivity : Activity() {
    private lateinit var binding: ActivityAdminSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminSignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}