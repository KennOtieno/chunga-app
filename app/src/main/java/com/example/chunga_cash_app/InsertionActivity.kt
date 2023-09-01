package com.example.chunga_cash_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chunga_cash_app.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class InsertionActivity: AppCompatActivity() {

    private lateinit var etName : EditText
    private lateinit var etPassword : EditText
    private lateinit var spinnerCourses: Spinner
    private lateinit var btnInsertData: Button
    private lateinit var studentDbRef: DatabaseReference
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //grabbing input & button ID
        etName = findViewById(R.id.etName)
        etPassword = findViewById(R.id.etPassword)
//        spinnerCourses = findViewById(R.id.spinnerCourses)
//        btnInsertData = findViewById(R.id.btnInsertData)

        studentDbRef = FirebaseDatabase.getInstance().getReference("Students")

//        val courses = arrayOf("Course 1", "Course 2", "Course 3")
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
//        spinnerCourses.adapter = adapter

        binding.btnInsertData.setOnClickListener {

            Log.d("Insertion Activity", "Button clicked")
            try {
                insertStudentData();
            }
            catch (e: Exception) {
                Log.e("Insertion Activity", "Error inserting student data", e)
            }
        }
    }
        // inserting Data function
        private fun insertStudentData() {
            var name: String = etName.text.toString()
            var password: String = etPassword.text.toString()
//            var course: String = spinnerCourses.selectedItem.toString()
            val student = Students(name, password) // creating a new student

            if (name.isEmpty()){
                etName.error = "Please enter student name"
            }

            if (password.isEmpty()){
                etPassword.error = "Please enter password"
            }

            studentDbRef.push().setValue(student) // pushing to database
            Toast.makeText(this@InsertionActivity,"Student Submitted", Toast.LENGTH_SHORT).show() // confirmation message
        }
    }







// listener for when user clicks button



        // Write a message to the database
//        val database = Firebase.database // creating a reference to data base (calling DB)
//        val adminsRef = database.getReference("admins")
//        val studentRef = database.getReference("students")
//
//        val admin = Admin("darrenharris2483", "school123", "darrenh@gmail.com", "USA")
//        val student = Student("darren", "harris", 30004852,"hello.jpg",8888,"55.23")
//
//        adminsRef.child("")


    // Read database
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val value = snapshot.getValue<String>()
//                Log.d(TAG, "Value is value")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w(TAG, "Failed to read value", error.toException())
//            }
//        })
