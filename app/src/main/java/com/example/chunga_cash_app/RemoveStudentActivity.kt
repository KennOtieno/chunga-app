package com.example.chunga_cash_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class RemoveStudentActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_student)

        val searchButton: Button = findViewById(R.id.searchButton)

        searchButton.setOnClickListener {
            // Implement logic to search for the student in Firebase
            // Display student information (name, admission number, class, photo)

            // Show a dialog to confirm account deletion
            showDeleteConfirmationDialog()
        }
    }

    private fun showDeleteConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Student Account")
            .setMessage("Are you sure you want to delete this student's account?")
            .setPositiveButton("Yes") { _, _ ->
                // User clicked Yes, navigate to RemoveConfirmationActivity
                val intent = Intent(this, RemoveConfirmationActivity::class.java)
                // Pass necessary data to RemoveConfirmationActivity if needed
                startActivity(intent)
            }
            .setNegativeButton("No") { _, _ ->
                // User clicked No, go back to the main activity
                finish()
            }

        val dialog = builder.create()
        dialog.show()
    }
}