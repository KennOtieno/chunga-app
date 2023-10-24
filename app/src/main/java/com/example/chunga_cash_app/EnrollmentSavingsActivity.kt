package com.example.chunga_cash_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_enrollment_savings.*

class EnrollmentSavingsActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enrollment_savings) // XML LAYOUT

        // Using "students" node in our Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().reference.child("students")

        // Fetch total number of students enrolled
        fetchTotalStudents()

        // Fetch and display total amount saved
        fetchTotalAmountSaved()
    }

    private fun fetchTotalStudents() {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Count the number of students in the dataSnapshot
                val totalStudents = dataSnapshot.childrenCount
                totalStudentsTextView.text = totalStudents.toString()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors here
            }
        })
    }

    private fun fetchTotalAmountSaved() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmountSaved = 0
                // Loop through dataSnapshot to calculate total amount saved
                for (studentSnapshot in dataSnapshot.children) {
                    val student = studentSnapshot.getValue(Student::class.java)
                    // Student class has an 'accountBalance'
                    student?.accountBalance?.let {
                        totalAmountSaved += it.toInt()
                    }
                }
                totalAmountSavedTextView.text = "$totalAmountSaved ksh"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors here
            }
        })
    }
}