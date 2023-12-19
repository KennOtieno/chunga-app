package com.example.chunga_cash_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import android.widget.ImageView
import com.example.chunga_cash_app.databinding.ActivityStudentCheckInBinding
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class StudentCheckInActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding : ActivityStudentCheckInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentCheckInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("students")

        // Fetch student data from database
        val studentKey = "adminNumText" // The actual student key
        databaseReference.child(studentKey).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val student = dataSnapshot.getValue(Students::class.java)

                if (student != null) {
                    // Display Student Information
                    val photoImageView: ImageView = findViewById(R.id.photoImageView)
                    // Loading image into photoImageView using Picasso
                    Picasso.get().load(student.photo).into(photoImageView)

                    val nameTextView = findViewById<TextView>(R.id.nameTextView)
                    val adminNumTextView = findViewById<TextView>(R.id.adminNumTextView)
                    val classStreamTextView = findViewById<TextView>(R.id.classStreamTextView)

                    nameTextView.text = student.name
                    adminNumTextView.text = student.adminNum.toString()
                    classStreamTextView.text = student.classStream

                    // Pin Input field and OK button
                    val pinEditText = findViewById<EditText>(R.id.pinEditText)
                    val okButton = findViewById<Button>(R.id.okButton)

                    okButton.setOnClickListener {
                        val enteredPin = pinEditText.text.toString()
                        val correctPin = student.pinCode.toString()

                        if (enteredPin == correctPin) {
                            // If it's the correct pin, navigate to StudentAccountActivity
                            val intent = Intent(this@StudentCheckInActivity, StudentAccountActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this@StudentCheckInActivity, "Successful", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@StudentCheckInActivity, "Incorrect Pin", Toast.LENGTH_SHORT).show()
                        }
                    }

                    // Forgot Pin Button ("It shall lead to an activity called 'ForgotPinActivity' when clicked)
                    val forgotPinCode = findViewById<Button>(R.id.forgotPinCode)
                    forgotPinCode.setOnClickListener {
                        // When clicked goes to Forgot Pin Activity
                        val intent = Intent(this@StudentCheckInActivity, ForgotPinActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this@StudentCheckInActivity, "Opening", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle the case where student data doesn't exist in the database
                    Toast.makeText(this@StudentCheckInActivity, "Not Found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle Errors
                Toast.makeText(this@StudentCheckInActivity, "Failed to Retrieve", Toast.LENGTH_SHORT).show()
            }
        })
    }
}