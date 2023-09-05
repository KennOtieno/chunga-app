package com.example.chunga_cash_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.chunga_cash_app.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var studentDbRef: DatabaseReference
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //grabbing input & button ID
//        etName = findViewById(R.id.etName)
//        etStudentPin = findViewById(R.id.etStudentPin)
//        etAdminNum = findViewById(R.id.etAdminNum)
//
//        dbName = etAdminNum.text.toString()
//        studentDbRef = FirebaseDatabase.getInstance().getReference("Students")
//        studentDbRef.child(dbName)


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
        var name = binding.etName.text.toString()
        var studentPinText = binding.etStudentPin.text.toString()
        var studentPin : Int? = studentPinText.toIntOrNull()
        var adminNumText = binding.etAdminNum.text.toString()
        var adminNum : Int? =  adminNumText.toIntOrNull()


        if (name.isEmpty()){
            binding.etName.error = "Please enter student name"
        }

        else if (studentPin == null) {
            binding.etStudentPin.error = "Please enter a 4 digit pin"
        }

        else if(adminNum == null) {
            binding.etAdminNum.error = "Please enter admission number"
        }

        else {
            studentDbRef = FirebaseDatabase.getInstance().getReference("Students")
            val student = Students(name, studentPin, adminNum) // creating a new student
            studentDbRef.child(adminNumText).setValue(student).addOnSuccessListener {
                binding.etAdminNum.text.clear()
                binding.etName.text.clear()
                binding.etStudentPin.text.clear()
                Toast.makeText(this@MainActivity, "Student Submitted", Toast.LENGTH_SHORT)
                    .show() // confirmation message
            }.addOnCanceledListener {
                Toast.makeText(this@MainActivity, "Student could not be added at this time.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}