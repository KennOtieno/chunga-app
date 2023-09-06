import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Widget
import kotlinx.android.synthetic.main.activity_main.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstateState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton.setonClickListener {
            showSearchDialog()
        }
    }

    private fun showSearchDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Search Admission Number")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK") { dialog, which ->
            // Get's admission number entered by user
            val admissionNumber = input.text.toString()

            // Query FireBase Realtime DataBase for the admission Number
            val databaseReference = Firebase.getInstance().getReference("admission_data")
            val query = databaseReference.orderByChild("admission_number").equalTo(admissionNumber)

            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {

                        val admissionInfo = dataSnapshot.getValue(YourDataModule::class.java)
                    }
                }
            })
            showToast("Search for admission number: $admissionumber")
        }

        builder.setNegativeButton ("Cancel") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}