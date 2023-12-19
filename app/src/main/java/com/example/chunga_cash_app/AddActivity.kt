package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {
    private lateinit var studentNameEditText: EditText
    private lateinit var admissionNumberEditText: EditText
    private lateinit var classAndStreamEditText: EditText
    private lateinit var pinCodeEditText: EditText
    private lateinit var accountBalanceEditText: EditText
    private lateinit var facePhotoEditText: EditText
    private lateinit var mpesaNumberEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add) // ask Ken about view binding change

        // Initialize EditText fields
        studentNameEditText = findViewById(R.id.studentNameEditText)
        admissionNumberEditText = findViewById(R.id.admissionNumberEditText)
        classAndStreamEditText = findViewById(R.id.classAndStreamEditText)
        pinCodeEditText = findViewById(R.id.pinCodeEditText)
        accountBalanceEditText = findViewById(R.id.accountBalanceEditText)
        facePhotoEditText = findViewById(R.id.facePhotoEditText)
        mpesaNumberEditText = findViewById(R.id.mpesaNumberEditText)

        val saveButton: Button = findViewById(R.id.saveButton)
        val cancelButton: Button = findViewById(R.id.cancelButton)

        saveButton.setOnClickListener {
            // Get user inputs from EditText fields
            val studentName = studentNameEditText.text.toString()
            val admissionNumber = admissionNumberEditText.text.toString()
            val admissionNumberInt = Integer.parseInt(admissionNumber)
            val classAndStream = classAndStreamEditText.text.toString()
            val pinCode = pinCodeEditText.text.toString()
            val pinCodeInt = Integer.parseInt(pinCode)
            val accountBalance = accountBalanceEditText.text.toString()
            val accountBalanceInt = Integer.parseInt(accountBalance)
            val facePhoto = facePhotoEditText.text.toString()
            val mpesa = mpesaNumberEditText.text.toString()
            val mpesaNumber = Integer.parseInt(mpesa)


            // Check if required fields are filled
            if (studentName.isEmpty() || admissionNumber.isEmpty() || classAndStream.isEmpty() || pinCode.isEmpty() || accountBalance.isEmpty() || facePhoto.isEmpty() || mpesa.isEmpty()) {
                // Show Toast
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                // Initialize Firebase Database
                val database = FirebaseDatabase.getInstance()
                val databaseReference = database.reference.child("students")

                // Create Student object with user input data
                val studentId = databaseReference.push().key
                val student = Students(studentName, admissionNumberInt, classAndStream, pinCodeInt, accountBalanceInt, facePhoto)

                // Save student object to the database
                if (studentId != null) {
                    databaseReference.child(studentId).setValue(student)
                        .addOnSuccessListener {
                            // Data successfully saved
                            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            // Error occurred while saving data
                            Toast.makeText(this, "Failed to save. Try Again", Toast.LENGTH_SHORT).show()
                        }
                }
                // Navigate back to MainActivity
                finish()
            }
        }

        cancelButton.setOnClickListener {
            // Navigate back to MainActivity without saving data
            finish()
        }
    }
}