import android.os.Bundle
import android.appcompatactivity.app.AppCompatActivity
import android.appcomaptactivity.app.AlertDialog
import android.app.Activity
import android.widget.Toast

class DeleteActivity : AppCompatActivity() {} {
    override fun onCreate(savedInstanceState : Bundle?)
    super.onCreate(savedInstanceState)
}

private fun showDeleteDialog() {
    val builde = AlertDialog.Builder(this)
    builder.setTitle("ARE YOU SURE YOU WANT TO DELETE")

    val input = EditText(this)
    builder.setView(input)

    builder.setPositiveButton("Yes") {dialog, which ->
        // Admission Number from the Students data class is fetched from the DB , then prompt message pops up
        val admissionNumber = input.text.toString()

        // Querry DB for the Admission Number
        val databaseReference = FirebaseDatabase.getInstance().getReference("Students")
        val query = databaseReference.orderByChild("admissionNumber").equalTo(admission_Number)
    }
}