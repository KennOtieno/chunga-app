package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.chunga_cash_app.AccountedMessagesActivity
import com.example.chunga_cash_app.databinding.ActivityTransactionDetailsBinding

class TransactionDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionDetailsBinding
    override fun  onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // XML LAYOUT DON'T FORGET

        val accountedMessages = findViewById<Button>(R.id.accountedMessages)
        val unaccountedMessages = findViewById<Button>(R.id.unaccountedMessages)

        accountedMessages.setOnClickListener {
            // When clicked, it navigates to AccountedDetailsActivity
            // Here you can see confirmation MPESA messages from MPESA numbers that were saved by students while enrolling in the app.
            val intent = Intent(this, AccountedMessagesActivity::class.java)
            startActivity(intent)
        }

        unaccountedMessages.setOnClickListener {
            // When clicked it navigates to UnaccountedDetailsActivity
            // Here you can see confirmation MPESA messages from MPESA numbers that aren't saved by students
            val intent = Intent(this, UnaccountedMessagesActivity::class.java)
            startActivity(intent)
        }
    }
}