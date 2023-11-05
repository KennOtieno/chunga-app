package com.example.chunga_cash_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.chunga_cash_app.databinding.ActivityStartupBinding
import com.example.chunga_cash_app.databinding.ActivityUserConfirmationBinding

class UserConfirmationActivity : Activity() {
    private lateinit var binding: ActivityUserConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserConfirmationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.schoolLoginBtn.setOnClickListener {
            val intent = Intent(this@UserConfirmationActivity, LogInActivity::class.java)
            startActivity(intent)
        }
    }
}