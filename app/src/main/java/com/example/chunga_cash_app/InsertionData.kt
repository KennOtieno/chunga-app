package com.example.chunga_cash_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class InsertionActivity: AppCompatActivity() {
    private lateinit var adminUsername : EditText
    private lateinit var adminPassword : EditText
    private lateinit var adminEmail : EditText
    private lateinit var adminInstitute : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Write a message to the database
        val database = Firebase.database // creating a reference to data base (calling DB)
        val adminsRef = database.getReference("admins")
        val studentRef = database.getReference("students")

        val admin = Admin("darrenharris2483", "school123", "darrenh@gmail.com", "USA")
        val student = Student("darren", "harris", 30004852,"hello.jpg",8888,"55.23")

        adminsRef.child("")


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
    }
}
