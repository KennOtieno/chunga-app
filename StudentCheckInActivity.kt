import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CheckInActivity : AppCompatActivity() {

    private lateinit var pinCodeEditText: EditText
    private lateinit var checkInButton: Button
    private lateinit var backButton: Button
    private lateinit var forgotPasswordButton: Button
    private lateinit var photoImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var admissionNumberTextView: TextView
    private lateinit var classTextView: TextView

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in) // XML LAYOUT

        // Initialize UI elements
        pinCodeEditText = findViewById(R.id.pinCodeEditText)
        checkInButton = findViewById(R.id.checkInButton)
        backButton = findViewById(R.id.backButton)
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton)
        photoImageView = findViewById(R.id.photoImageView)
        nameTextView = findViewById(R.id.nameTextView)
        admissionNumberTextView = findViewById(R.id.admissionNumberTextView)
        classTextView = findViewById(R.id.classTextView)

        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("students")

        // Retrieve student details from Firebase based on user's authentication ID
        val userId = firebaseAuth.currentUser?.uid
        userId?.let {
            databaseReference.child(it).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val name = snapshot.child("name").getValue(String::class.java)
                        val admissionNumber = snapshot.child("admissionNumber").getValue(String::class.java)
                        val className = snapshot.child("class").getValue(String::class.java)
                        // Set student details in UI
                        nameTextView.text = "NAME: $name"
                        admissionNumberTextView.text = "ADMISSION NO: $admissionNumber"
                        classTextView.text = "CLASS: $className"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error if needed
                }
            })
        }

        // Check-in button click listener
        checkInButton.setOnClickListener {
            val enteredPin = pinCodeEditText.text.toString()
            // Validate entered pin with the pin saved in Firebase
            // Implement your own logic to fetch and validate the pin from Firebase
            if (enteredPin == "1234") { // Replace "1234" with the correct pin fetched from Firebase
                // Correct pin, navigate to StudentAccountActivity
                val intent = Intent(this, StudentAccountActivity::class.java)
                startActivity(intent)
            } else {
                // Incorrect pin, show toast message
                Toast.makeText(this, "! Wrong Pin Code. Try Again.", Toast.LENGTH_SHORT).show()
            }
        }

        // Back button click listener
        backButton.setOnClickListener {
            finish() // Close this activity and go back to the previous screen
        }

        // Forgot Password button click listener
        forgotPasswordButton.setOnClickListener {
            val intent = Intent(this, StudentForgotPinActivity::class.java)
            startActivity(intent)
        }
    }
}