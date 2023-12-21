package com.example.chunga_cash_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class RemoveStudentActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var admissionNumberEditText: EditText
    private lateinit var okButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_student)

        // Initialize Database
        databaseReference = FirebaseDatabase.getInstance().reference.child("students")

        admissionNumberEditText = findViewById(R.id.admissionNumberEditText)
        okButton = findViewById(R.id.okButton)

        okButton.setOnClickListener {
            // Get the admission number entered by the user
            val admissionNumber = admissionNumberEditText.text.toString()

            // Implement logic to search for the student in Firebase
            searchForStudent(admissionNumber)
        }
    }

    private fun searchForStudent(admissionNumber: String) {
        // Query Firebase to check if the admission number exists
        databaseReference.child(admissionNumber)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // Student exists, show RemoveConfirmationActivity
                        val intent = Intent(
                            this@RemoveStudentActivity,
                            RemoveConfirmationActivity::class.java
                        )
                        // Pass necessary data to RemoveConfirmationActivity if needed
                        startActivity(intent)
                    } else {
                        // Student does not exist, show a toast
                        Toast.makeText(
                            this@RemoveStudentActivity,
                            "Admission number does not exist",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle the error, if any
                    Toast.makeText(
                        this@RemoveStudentActivity,
                        "Error: An Error Occured Try Again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}