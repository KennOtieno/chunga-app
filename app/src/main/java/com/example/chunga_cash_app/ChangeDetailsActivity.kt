import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.app.AlertDialog
import android.content.DialogInterface
import com.google.firebase.database.FirebaseDatabase
import androidx.appcompat.app.AppCompatActivity

class ChangeDetailsActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var institutionNameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_details) // XML LAYOUT HERE DON'T FORGET IT.

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        emailEditText = findViewById(R.id.emailEditText)
        institutionNameEditText = findViewById(R.id.institutionNameEditText)

        val saveButton: Button = findViewById(R.id.saveButton)
        val cancelButton: Button = findViewById(R.id.cancelButton)

        saveButton.setOnClickListener {
            showConfirmationDialog()
        }

        cancelButton.setOnClickListener {
            finish() // Closes this activity and goes back to the previous screen (Administration Activity)
        }
    }

    private fun showConfirmationDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Confirmation")
        alertDialogBuilder.setMessage("Are you sure you want to change Administration Details?")
        alertDialogBuilder.setPositiveButton("Yes") { dialog, _ ->
            saveUserDetailsToFirebase()
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun saveUserDetailsToFirebase() {
        // Get user input from EditText fields
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()
        val email = emailEditText.text.toString()
        val institutionName = institutionNameEditText.text.toString()

        // Save the user details to Firebase Realtime Database
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("admin")

        // The Admin data class constructor takes these parameters
        val admin = Admin(username, password, email, institutionName)

        // Push the new admin details to the database
        ref.push().setValue(admin)

        // Show a toast message indicating that the details have been saved
        Toast.makeText(this, "Saved.", Toast.LENGTH_SHORT).show()

        // Close this activity and go back to the previous screen (Administration Activity)
        finish()
    }
}