package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.google.firebase.database.*

class StudentAccountActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var student: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_account)

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("students")

        // Fetch Student Data from database
        val studentKey = "the_student_key" // We have to Replace this with the actual key on the DB.
        databaseReference.child(studentKey).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                student = dataSnapshot.getValue(Studemt::class.java) ?: Student()

                // Display Student Information
                val photoImageView: ImageView = findViewById(R.id.photoImageView)
                // Loading Image with picasso
                Picasso.get().load(student.photo).into(photoImageView)

                val nameTextView = findViewById<TextView>(R.id.nameTextView)
                val classStreamTextView = findViewById<TextView>(R.id.classStreamTextView)
                val adminNumTextView = findViewById<TextView>(R.id.adminNumTextView)
                val accountBalanceTextView = findViewById<TextView>(R.id.accountBalanceTextView)

                nameTextView.text = student.name
                classStreamTextView.text = student.classStream
                adminNumTextView.text = student.adminNum.toString()
                accountBalanceTextView.text = student.accountBalance.toString()

                // BACK Button
                val backButton: Button = findViewById(R.id.backButton)
                backButton.setOnClickListener {
                    onBackPressed()
                }

                // ADD Button
                val addButton: Button = findViewById(R.id.addButton)
                addButton.setOnClickListener {
                    showInputDialog(true)
                }

                // DEDUCT Button
                val deductButton: Button = findViewById(R.id.deductButton)
                deductButton.setOnClickListener {
                    showInputDialog(false)
                }

                // Drop-down menu Button
                val menuButton = Button = findViewById(R.id.menuButton)
                menuButton.setOnClickListener {
                    showPopupMenu(menuButton)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle Errors
            }
        }
    }
}