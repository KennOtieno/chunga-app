package com.example.chunga_cash_app

import android.app.Activity
import android.os.Bundle
import com.example.chunga_cash_app.databinding.ActivityStartupBinding
import android.content.Intent


class StartupActivity : Activity() {
    private lateinit var binding: ActivityStartupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.signUpBtn.setOnClickListener {
            val intent = Intent(this@StartupActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}