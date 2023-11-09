package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.google.firebase.database.*
import java.awt.PopupMenu

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
                Toast.makeText(this@StudentAccountActivity, "Failed to Retrieve", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showInputDialog(isAddOperation: Boolean) {
        val inputEditText = EditText(this@StudentAccountActivity)
        val dialogTitle = if (isAddOperation) "Add Amount" else "Deduct Amount"

        val dialog = AlertDialog.Builder(this@StudentAccountActivity)
            .setTitle(dialogTitle)
            .setView(inputEditText)
            .setPositiveButton("OK") { dialog, _ ->
                val inputAmount = inputEditText.text.toString().toIntorNull() ?: 0
                if(isAddOperation) {
                    student.accountBalance += inputAmount
                } else {
                    student.accountBalance = maxOf(0, student.accountBalance - inputAmount)
                }
                // Update the account balance in the Firebase DB
                databaseReference.child(studentKey).setValue(student)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this@StudentAccountActivity, view)
        popupMenu.menuInflater.inflate(R.menu.menu_student_account, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.menu_my_transactions -> {
                    // Start My Transaaction Activity when Clicked.
                    startActivity(Intent(this@StudentAccountActivity, MyTransactionActivity::class.java))
                    true
                }
                R.id.menu_my_account -> {
                    // Start My Account Activity when clicked
                    startActivity(Intent(this@StudentAccountActivity, MyAccountActivity::class.java))
                    true
                }
                R.id.menu_my_pin -> {
                    // Start My Pin Activity when clicked
                    startActivity(Intent(this@StudentAccountActivity, MyPinActivity::class.java))
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}