package com.example.chunga_cash_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.chunga_cash_app.databinding.ActivityUnaccountedMessagesBinding
import com.google.firebase.database.*

class UnaccountedMessagesActivity : AppCompatActivity() {

    private lateinit var messagesTextView: TextView
    private lateinit var binding: ActivityUnaccountedMessagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnaccountedMessagesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // XML LAYOUT

        messagesTextView = findViewById(R.id.messagesTextView) // I am Assuming we have a TextView in XML layout, CHECK ON THIS

        // Retrieve unaccounted MPESA confirmation messages from Firebase via mpesa node in the DB
        val messagesRef = FirebaseDatabase.getInstance().getReference("mpesa")
        messagesRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val messagesList = mutableListOf<String>()
                for (messageSnapshot in snapshot.children) {
                    val message = messageSnapshot.getValue(String::class.java)
                    if (message != null) {
                        messagesList.add(message)
                    }
                }
                displayMessages(messagesList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error if needed
            }
        })
    }

    private fun displayMessages(messages: List<String>) {
        val formattedMessages = messages.joinToString(separator = "\n\n")
        messagesTextView.text = formattedMessages
    }
}