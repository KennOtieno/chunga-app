package com.example.chunga_cash_app

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.example.chunga_cash_app.databinding.ActivityStudentAccountBinding
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import java.awt.PopupMenu

class StudentAccountActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var student: Students
    private lateinit var binding: ActivityStudentAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("students")

        // Fetch Student Data from database
        val studentKey = "adminNumText" // This is the actual key in the DB.
        databaseReference.child(studentKey).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                student = dataSnapshot.getValue(Students::class.java) ?: Students()

                // Display Student Information
                val photoImageView: ImageView = binding.photoImageView
                // Loading Image with picasso
                Picasso.get().load(student.photo).into(photoImageView)

                val nameTextView = binding.nameTextView
                val classStreamTextView = binding.classStreamTextView
                val adminNumTextView = binding.adminNumTextView
                val accountBalanceTextView = binding.accountBalanceTextView

                nameTextView.text = student.name
                classStreamTextView.text = student.classStream
                adminNumTextView.text = student.adminNum.toString()
                accountBalanceTextView.text = student.accountBalance.toString()

                // BACK Button
                val backButton: Button = binding.backButton
                backButton.setOnClickListener {
                    onBackPressed() // deprecated || might need to use getOnBackPressedDispatcher()
                }

                // DEPOSIT  Button
                val depositButton: Button = binding.depositButton
                depositButton.setOnClickListener {
                    showInputDialog(true)
                }

                // WITHDRAW Button
                val withdrawButton: Button = binding.withdrawButton
                withdrawButton.setOnClickListener {
                    showInputDialog(false)
                }

                // Drop-down menu Button
                val menuButton : Button = binding.menuButton
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
                val inputAmount = inputEditText.text.toString().toIntOrNull() ?: 0
                if (isAddOperation) {
                    student.accountBalance += inputAmount
                } else {
                    if (inputAmount > student.accountBalance) {
                        // Display a message to the user indicating insufficient funds
                        Toast.makeText(this@StudentAccountActivity, "Insufficient funds. Withdrawal unsuccessful.", Toast.LENGTH_SHORT).show()
                    } else {
                        student.accountBalance -= inputAmount
                        // Update the account balance in the Firebase DB
                        databaseReference.child(studentKey).setValue(student)
                        dialog.dismiss()
                    }
                }
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