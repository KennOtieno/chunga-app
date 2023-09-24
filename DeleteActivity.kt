import android.os.Bundle
import android.appcompatactivity.app.AppCompatActivity
import androidx.appcomaptactivity.app.AlertDialog
import android.app.Activity
import android.widget.Toast
import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

private fun showConfirmationDialog() {
    val builder = AlertDialog.Builder(this)
    builder.setTitle("ARE YOU SURE YOU WANT TO DELETE")

    val input = EditText(this)
    builder.setView(input)

    builder.setPositiveButton("Yes") {dialog, which ->
        // Fetch Admission Number from database
        val admissionNumber = input.text.toString()

        // Querry DB for the Admission Number
        val databaseReference = FirebaseDatabase.getInstance().getReference("Students")
        val query = databaseReference.orderByChild("admissionNumber").equalTo(admissionNumber)
    }

    builder.setNegativeButton("No") {dialog, which ->
        dialog.cancel()
    }

    builder.show()
}

private fun showAdminPasswordDialog() {
    val builde = AlertDialog.Builder(this)
    builder.setTitle("INSERT ADMIN PASSWORD")

    val input = EditText(this)
    builder.setView(input)

    builder.setPositiveButtton("OK") {dialog, which ->
        // Fetch Admin Password from database
        val password = input.text.toString()

        // Querry DataBase for Admin Password
        val databaseReference = FirebaseDatabase.getInstance().getReference("Admin")
        val query = databaseReference.orderByChild("password").equalTo(password)

        // Check if password is correct
        querry.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapShot) {
                // If password is correct proceed with Deletion
                showToast("Removed - Delete")
            } else {
                // If password is incorrect we give error message with '!' in colour red
                showToast("Incorrect Password, Try Again - Not Deleted")
            }
        })
    }

    builder.setNegativeButton("Cancel") {dialog, which ->
        dialog.cancel()
    }

    builder.show()
}