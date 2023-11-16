package com.example.chunga_cash_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class MyTransactionActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var transactionAdapter: TransactionAdapter
    private var studentKey: String = "adminNumText" // Replace with the actual key on the DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_transaction)

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("students")

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.transactionRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        transactionAdapter = TransactionAdapter()
        recyclerView.adapter = transactionAdapter

        // Fetch Student Data from database
        databaseReference.child(studentKey).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val student = dataSnapshot.getValue(Student::class.java)

                if (student != null) {
                    // Display Student's Transactions
                    if (student.transactions != null) {
                        transactionAdapter.setTransactions(student.transactions)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle Errors
                // You might want to show a toast or handle the error appropriately
            }
        })
    }
}