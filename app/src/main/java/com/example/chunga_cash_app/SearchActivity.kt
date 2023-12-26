package com.example.chunga_cash_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class SearchActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var okButton: Button
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchEditText = findViewById(R.id.editTextAdmission)
        okButton = findViewById(R.id.buttonOK)

        // Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("students")

        okButton.setOnClickListener {
            val admissionNumber = searchEditText.text.toString().trim()

            if (admissionNumber.isNotEmpty()) {
                searchStudent(admissionNumber)
            } else {
                showToast("Please enter an admission number")
            }
        }
    }

    private fun searchStudent(admissionNumber: String) {
        val query = databaseReference.orderByChild("admissionNumber").equalTo(admissionNumber)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Admission number exists, navigate to DiscoverStudentActivity
                    val intent = Intent(this@SearchActivity, DiscoverStudentActivity::class.java)
                    intent.putExtra("admissionNumber", admissionNumber)
                    startActivity(intent)
                } else {
                    showToast("Admission Number Does Not Exist")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                showToast("Error occurred: ${databaseError.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}