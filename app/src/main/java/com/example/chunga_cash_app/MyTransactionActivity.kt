package com.example.chunga_cash_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import android.widget.Toast
import com.example.chunga_cash_app.databinding.ActivityMyTransactionBinding

class MyTransactionActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var transactionAdapter: TransactionAdapter
    private var studentKey: String = "adminNumText" // This is the actual key on the DB
    private lateinit var binding: ActivityMyTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyTransactionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("students").child(studentKey)

        // Initialize RecyclerView
        recyclerView = binding.transactionRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        transactionAdapter = TransactionAdapter()
        recyclerView.adapter = transactionAdapter

        // Fetch Transactions Data from database
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val transactions = mutableListOf<Transaction>()

                for (transactionSnapshot in dataSnapshot.children) {
                    val transaction = transactionSnapshot.getValue(Transaction::class.java)
                    transaction?.let { transactions.add(it) }
                }

                // Update RecyclerView with transactions
                transactionAdapter.setTransactions(transactions)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle Errors
                val errorMessage = "An Error Occured"
                Toast.makeText(this@MyTransactionActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}