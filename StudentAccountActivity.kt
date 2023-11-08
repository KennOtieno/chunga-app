package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.google.firebase.database.*

class StudentAccountActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var student: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_account)

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("students")

        // Fetch Student Data from database
        val studentKey = "the_student_key" // We have to Replace this with the actual key on the DB.
        databaseReference.child(studentKey).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                student = dataSnapshot.getValue(Studemt::class.java) ?: Student()

                // Display Student Data
            }
        }
    }
}