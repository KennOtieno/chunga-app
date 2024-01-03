package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class DiscoverStudentActivity : AppCompatActivity() {

    private lateinit var studentPhotoImageView: ImageView
    private lateinit var studentNameTextView: TextView
    private lateinit var admissionNumberTextView: TextView
    private lateinit var classStreamTextView: TextView

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover_student)

        studentPhotoImageView = findViewById(R.id.imageViewStudentPhoto)
        studentNameTextView = findViewById(R.id.textViewStudentName)
        admissionNumberTextView = findViewById(R.id.textViewAdmissionNumber)
        classStreamTextView = findViewById(R.id.textViewClassStream)

        // Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("students")

        // Get admission number from intent
        val admissionNumber = intent.getStringExtra("admissionNumber")

        if (admissionNumber != null) {
            fetchStudentDetails(admissionNumber)
        }
    }

    private fun fetchStudentDetails(admissionNumber: String) {
        val query = databaseReference.orderByChild("adminNum").equalTo(admissionNumber)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Extract student details
                    val student = dataSnapshot.children.first().getValue(Students::class.java)

                    // Display details in the UI
                    if (student != null) {
                        displayStudentDetails(student)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
            }
        })
    }

    private fun displayStudentDetails(student: Students) {
        // Load and display student photo using Picasso
        Picasso.get().load(student.photo).into(studentPhotoImageView)

        // Set student name, admission number, and class stream in TextViews
        studentNameTextView.text = "Name: ${student.name}"
        admissionNumberTextView.text = "Admission Number: ${student.adminNum}"
        classStreamTextView.text = "Class Stream: ${student.classStream}"
    }
}