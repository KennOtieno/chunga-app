package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.chunga_cash_app.databinding.ActivityForgotPinBinding
import com.google.firebase.database.*

class ForgotPinActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding: ActivityForgotPinBinding
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // XML Layout

        // Initialise Firebase Database
        databaseReference = FirebaseDatabase.getInstance().reference.child("admin")

        // Find Views
        val adminPasswordEditText = binding.adminPasswordEditText
        val okButton = findViewById<Button>(R.id.okButton)

        // Setting up Ok button On Click Listener
        okButton.setOnClickListener {
            val enteredAdminPassword = adminPasswordEditText.text.toString()

            // Fetch Admin Password from the database
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val correctAdminPassword = dataSnapshot.getValue(String::class.java)

                    if (enteredAdminPassword == correctAdminPassword) {
                        // If the admin password is correct we go to Student Account Activity
                        val intent = Intent(this@ForgotPinActivity, StudentAccountActivity::class.java)
                        startActivity(intent)

                        // We show a Toast telling user to check their pin so that they won't forget it again
                        Toast.makeText(this@ForgotPinActivity, "Check or Change your Pin", Toast.LENGTH_SHORT).show()
                    } else {
                        // We show a Toast to the user saying that the Inputted Admin Password is Incorrect
                        Toast.makeText(this@ForgotPinActivity, "Incorrect Administrator Password! Try Again.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle DB Error
                    Toast.makeText(this@ForgotPinActivity, "OOPS! An Error Occured.Try Again.", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}