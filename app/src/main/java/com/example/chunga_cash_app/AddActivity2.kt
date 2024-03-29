//package com.example.chunga_cash_app
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Spinner
//import android.widget.Toast
//import androidx.appcompat.app.AlertDialog
//import com.example.chunga_cash_app.databinding.ActivityMainBinding
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import com.google.firebase.database.ktx.values
//import com.google.firebase.ktx.Firebase
//import java.lang.Exception
//
//class InsertionActivityActivity : AppCompatActivity() {
//
//    private lateinit var studentDbRef: DatabaseReference
//    private lateinit var binding: ActivityMainBinding
//    private  var admissionNum: Int? = 0
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//        binding.btnInsertData.setOnClickListener {
//            Log.d("Insertion Activity", "Button clicked") // check logcat
//            try {
//                insertStudentData();
//            } catch (e: Exception) {
//                Log.e("Insertion Activity", "Error inserting student data", e) //check logcat
//            }
//        }
//    }
//
//    // inserting student data to firebase
//    private fun insertStudentData() {
//        var name = binding.etName.text.toString()
//
//        // collecting pin as String then converting to int
//        var studentPinText = binding.etStudentPin.text.toString()
//        var studentPin: Int? = studentPinText.toIntOrNull()
//
//        //collecting admin number as String then converting to int
//        var adminNumText = binding.etAdminNum.text.toString()
//        var adminNum: Int? = adminNumText.toIntOrNull()
//        admissionNum = adminNum
//
//
//        // Checking if input values are empty
//        if (name.isEmpty()) {
//            binding.etName.error = "Please enter student name"
//        } else if (studentPin == null) {
//            binding.etStudentPin.error = "Please enter a 4 digit pin"
//        } else if (adminNum == null) {
//            binding.etAdminNum.error = "Please enter admission number"
//        }
//
//        // Otherwise add to database
//        else {
//            studentDbRef = FirebaseDatabase.getInstance().getReference("Students")
//            val student = Students(name, studentPin, adminNum) // creating a new student
//            studentDbRef.child(adminNumText).setValue(student).addOnSuccessListener {
//                binding.etAdminNum.text.clear()
//                binding.etName.text.clear()
//                binding.etStudentPin.text.clear()
//                showToast("Student Submitted") // confirmation message
//                // if fails, show toast message
//            }.addOnCanceledListener {
//                showToast("Student could not be added at this time.")
//            }
//        }
//    }
//
//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
//}