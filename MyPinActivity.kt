package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.google.firebase.database.*

class MyPinActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pin) // XML layout file

        // Initialize firebase database
        databaseReference = FirebaseDatabase.getInstance().reference.child("students")

        // Fetch students data from the database
        val studentKey = "your_student_key" // Replace this with the DB Actual Key otherwise ERROR will be here
        databaseReference.child(studentKey).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val student = dataSnapshot.getValue(Student::class.java)

                if (student != null) {
                    // Display Student's Pin in Text View
                    val pinTextView = findViewById<TextView>(R.id.pinTextView)
                    pinTextView.text = student.pinCode
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