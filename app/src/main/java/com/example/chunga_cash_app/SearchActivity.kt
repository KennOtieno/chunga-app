package com.example.chunga_cash_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class SearchActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        databaseReference = FirebaseDatabase.getInstance().getReference("Students")
        searchEditText = findViewById(R.id.editTextAdminNumber)
        val okButton: Button = findViewById(R.id.buttonOk)

        okButton.setOnClickListener {
            val adminNumInput = searchEditText.text.toString().trim()

            if (adminNumInput.isNotEmpty()) {
                checkAdmissionNumber(adminNumInput)
            } else {
                showToast("Please enter Admission Number")
            }
        }
    }

    private fun checkAdmissionNumber(adminNumInput: String) {
        databaseReference.child(adminNumInput).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // Admission Number exists, go to ShowStudentDetails activity
                    val intent = Intent(this@SearchActivity, ShowStudentDetails::class.java)
                    intent.putExtra("adminNumber", adminNumInput)
                    startActivity(intent)
                    finish()
                } else {
                    // Admission Number does not exist, show a toast
                    showToast("Admission Number does not exist")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                showToast("Error checking Admission Number: ${error.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}