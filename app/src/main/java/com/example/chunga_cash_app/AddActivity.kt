import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {
    private lateinit var studentNameEditText: EditText
    private lateinit var admissionNumberEditText: EditText
    private lateinit var classAndStreamEditText: EditText
    private lateinit var pinCodeEditText: EditText
    private lateinit var accountBalanceEditText: EditText
    private lateinit var mpesaNumberEditText: EditText
    private lateinit var facePhotoEditText: EditText

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.Layout.activity_add) // xml layout

        // Initialize Edit Texts
        studentNameEditText = findViewById(R.id.studentNameEditText)
        admissionNumberEditText = findViewById(R.id.admissionNumberEditText)
        classAndStreamEditText = findViewById(R.id.classAndStreamEditText)
        pinCodeEditText = findViewById(R.id.pinCodeEdiitText)
        accountBalanceEditText = findViewById(R.id.accountBalanceEditText)
        facePhotoEditText = findViewById(R.id.facePhotoEditText)
        mpesaNumberEditText = findViewById(R.id.mpesaNumberEditText)

        val saveButton: Button = findViewById(R.id.saveButton)
        val cancelButton: Button = findViewById(R.id.cancelButton)

        saveButton.setOnClickListener {
            // Get user inputs from Initialization
            val studentNameEditText = studentNameEditText.text.toString()
            val admissionNumberEditText = admissionNumberEditText.text.toString()
            val classAndStreamEditText = classAndStreamEditText.text.toString()
            val pinCodeEditText = pinCodeEditText.text.toString()
            val accountBalanceEditText = accountBalanceEditText.text.toString()
            val facePhotoEditText = facePhotoEditText.text.toString()

            // Check if everything is filled
            if (studentName.isEmpty() || admissionNumber.isEmpty() || classAndStream.isEmpty() || pinCode.isEmpty() || accountBalance.isEmpty() || facePhoto.isEmpty()) {
                // Show Toast
                Toast.makeText(this, "! All fields are required",Toast.LENGTH_SHORT).show()
            } else {
                // If all required fields are filled we proceed to save the data to the database
                // Implementint it to the databse
                val database = FireBase.getInstance()
                val databaseReference = database.reference.child("students")

                // Create Student object with user input data
                val studentId = dataReference.push().key
                val student = Student(studentId, studentName, admissionNumber, classAndStream, accountBalance, pinCode, facePhoto, mpesaNumber)

                // Save student object to the database
                if (studentId != null) {
                    databaseReference.child(studentId).setValue(student)
                        .addOnSuccessListener {
                            // Data successfully saved
                            Toast.makeText(this, "Saved successfully",Toast.LENGTH_SHORT.show())
                        }
                        .addOnFailureListener {
                            // Error occured while saving data
                            Toast.makeText(this, "! Oops Failed to save.Try Again",Toast.LENGTH_SHORT.show())
                        }
                }
                // Navigate to MainActivity
                finish()

                cancelButton.setOnClickListener {
                    // Navigate back to MainActivity without saving data
                    finish()
                }
            }



        }
    }
}