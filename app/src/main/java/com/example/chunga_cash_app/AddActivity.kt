import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

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

        // Initalize Edit Texts
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
            }



        }
    }
}