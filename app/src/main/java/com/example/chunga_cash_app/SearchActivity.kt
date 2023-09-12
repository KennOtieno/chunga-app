package com.example.chunga_cash_app

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.app.Activity
import android.widget.EditText
import android.widget.Toast
import com.example.chunga_cash_app.databinding.ActivitySearchBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

//import kotlinx.android.synthetic.main.activity_main.*


class SearchActivity : Activity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adminNumInput = intent.getStringExtra("adminInput").toString()
        databaseReference = FirebaseDatabase.getInstance().getReference("Students")
        databaseReference.child(adminNumInput).get().addOnSuccessListener {
            if(it.exists()) {
                val name =  it.child("name").value
                binding.tvname.text = name.toString()
                binding.tvAdminNumber.text = adminNumInput
            }
        }
//
//        binding.searchButton.setOnClickListener {
//            showSearchDialog()
//        }
//    }
//
//    private fun showSearchDialog() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Search Admission Number")
//
//        val input = EditText(this)
//        builder.setView(input)
//
//        builder.setPositiveButton("OK") { dialog, which ->
//            // Get's admission number entered by user
//            val admissionNumber = input.text.toString()
//
//            // Query FireBase Realtime DataBase for the admission Number
//            val databaseReference = FirebaseDatabase.getInstance().getReference("Students")
//            val query = databaseReference.orderByChild("admission_number").equalTo(admissionNumber)
//
//            query.addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                    if (dataSnapshot.exists()) {
//
//                        //val admissionInfo = dataSnapshot.getValue(YourDataModule::class.java)
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//            })
//            showToast("Search for admission number: $admissionNumber")
//        }
//
//        builder.setNegativeButton ("Cancel") { dialog, which ->
//            dialog.cancel()
//        }
//
//        builder.show()
//    }
//    private fun showToast(message: String) {
//
//
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}