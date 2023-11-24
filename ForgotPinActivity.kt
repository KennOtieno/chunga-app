package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import android.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class ForgotPinActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pin) // XML Layout

        // Initialise Firebase Database
        databaseReference = FirebaseDatabase.getInstance().reference.child("admin")

        // Find Views
        val adminPasswordEditText = findViewById<EditText>(R.id.adminPasswordEditText)
        val okButton = findViewById<Button>(R.id.okButton)

        // Setting up Ok button On Click Listener
        okButton.setOnClickListener {
            val enteredAdminPassword = adminPasswordEditText.text.toString()

            // Fetch Admin Password from the database
            databaseReference.addLiistenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val correctAdminPassword = dataSnapshot.getValue(String::class.java)

                    if (enteredAdminPassword == correctAdminPassword) {
                        // If the admin password is correct we go to Student Account Activity
                        val intent = Intent(this@ForgotPinActivity, StudentAccountActivity::class.java)
                        startActivity(intent)

                        // We show a Toast telling user to check their pin so that they won't forget it again
                        Toast.makeText(
                            this@ForgotPinActivity,
                            "Check or Change your Pin"
                            Toast.LENGTH_SHORT
                        ).show()
                    } else
                }
            }
        }
    }
}