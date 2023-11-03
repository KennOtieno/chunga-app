package com.example.chunga_cash_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class SearchActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        databaseReference = FirebaseDatabase.getInstance().getReference("Students")
        val adminNumInput = intent.getStringExtra("adminInput").toString()

        databaseReference.child(adminNumInput).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val name = snapshot.child("name").value
                val adminNumber = snapshot.key
                // Update your UI elements with name and adminNumber
            }
        }
    }
}