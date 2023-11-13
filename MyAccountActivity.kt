package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.database.*

class MyAccountActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account) // XML layout file

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("students")

        // Fetch students data from the database
        val studentKey = "your_student_key" // Replace this with the actual student key
        databaseReference.child(studentKey).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val student = dataSnapshot.getValue(Student::class.java)

                if (student != null) {
                    // Display Student's Details in EditText
                    val photoEditText = findViewById<EditText>(R.id.photoEditText)
                    val nameEditText = findViewById<EditText>(R.id.nameEditText)
                    val adminNumEditText = findViewById<EditText>(R.id.adminNumEditText)
                    val classStreamEditText = findViewById<EditText>(R.id.classStreamEditText)
                    val mpesaEditText = findViewById<EditText>(R.id.mpesaEditText)

                    photoEditText.setText(student.photo)
                    nameEditText.setText(student.name)
                    adminNumEditText.setText(student.adminNum.toString())
                    classStreamEditText.setText(student.classStream)
                    mpesaEditText.setText(student.mpesa.toString())

                    // Change Button Click Listener
                    val changeButton = findViewById<Button>(R.id.changeButton)
                    changeButton.setOnClickListener {
                        showChangeConfirmationDialog(studentKey)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle Errors
                Toast.makeText(this@MyAccountActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showChangeConfirmationDialog(studentKey: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Change Details")
        alertDialogBuilder.setMessage("Are you sure you want to change your details?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            // Update details in the database (you need to implement this part based on your requirements)
            updateDetailsInDatabase(studentKey)
        }
        alertDialogBuilder.setNegativeButton("No") { _, _ ->
            // Do nothing, user canceled the change
        }
        alertDialogBuilder.show()
    }

    private fun updateDetailsInDatabase(studentKey: String) {
        // You need to implement the logic to update details in the database based on user input
        // For example, get the updated values from the EditText fields and update the corresponding fields in the database

        // After updating the details, show a toast indicating that the details have been changed
        Toast.makeText(this@MyAccountActivity, "Changed", Toast.LENGTH_SHORT).show()
    }
}