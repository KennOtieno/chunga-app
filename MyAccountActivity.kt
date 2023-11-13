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
        val studentKey = "your_student_key" // Replace this with the actual student key, THIS IS IMPORTANT DARREN
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
            // Update details in the database
            updateDetailsInDatabase(studentKey)
        }
        alertDialogBuilder.setNegativeButton("No") { _, _ ->
            // Do nothing, user canceled the change
        }
        alertDialogBuilder.show()
    }

    private fun updateDetailsInDatabase(studentKey: String) {
        // Get the updated values from the EditText fields
        val updatedPhoto = findViewById<EditText>(R.id.photoEditText).text.toString()
        val updatedName = findViewById<EditText>(R.id.nameEditText).text.toString()
        val updatedAdminNum = findViewById<EditText>(R.id.adminNumEditText).text.toString()
        val updatedClassStream = findViewById<EditText>(R.id.classStreamEditText).text.toString()
        val updatedMpesa = findViewById<EditText>(R.id.mpesaEditText).text.toString()

        // I had to create a HashMap to represent the updated data (*I have to confirm if this is correct I think it is.*)
        val updatedData = hashMapOf<String, Any?>(
            "photo" to updatedPhoto,
            "name" to updatedName,
            "adminNum" to updatedAdminNum.toInt(), // This is an Int
            "classStream" to updatedClassStream,
            "mpesa" to updatedMpesa.toInt() // It is also an integer
        )

        // Update the corresponding fields in the database
        databaseReference.child(studentKey).updateChildren(updatedData)
            .addOnSuccessListener {
                // On success, show a toast indicating that the details have been changed
                Toast.makeText(this@MyAccountActivity, "Changed", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                // On failure, show a toast indicating an error
                Toast.makeText(this@MyAccountActivity, "Failed to update details, Try Again.", Toast.LENGTH_SHORT).show()
            }
    }
}