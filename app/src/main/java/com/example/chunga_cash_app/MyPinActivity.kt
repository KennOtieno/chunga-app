package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.chunga_cash_app.databinding.ActivityMyPinBinding
import com.google.firebase.database.*

class MyPinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPinBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize firebase database
        databaseReference = FirebaseDatabase.getInstance().reference.child("students")

        // Fetch students data from the database
        val studentKey = "adminNumText" // Official StudentKey as used in the DB
        databaseReference.child(studentKey).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val student = dataSnapshot.getValue(Students::class.java)

                if (student != null) {
                    // Display Student's Pin in Text View
                    binding.pinTextView.text = student.pinCode.toString()
                } else {
                    // Show Toast when the data doesn't exist
                    Toast.makeText(this@MyPinActivity, "Does not Exist", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Show Toast saying Cancelled or Error
                Toast.makeText(this@MyPinActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}