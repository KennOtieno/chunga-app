import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent

class AdministrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administration) // XML layout file

        val enrollmentSavings = findViewById<Button>(R.id.enrollmentSavings)
        val changeDetails = findViewById<Button>(R.id.changeDetails)
        val transactionDetails = findViewById<Button>(R.id.transactionDetails)

        // set the buttons On Click Listener
        enrollmentSavings.setOnClickListener {
            // When clicked it opens up the Enrollment and Savings Activity
            // User can see total number of students enrolled in the app & Total Amount saved
            val intent = Intent(this, EnrollmentSavingsActivity::class.java)
            startActivity(intent)
        }

        changeDetails.setOnClickListener {
            // When clicked it opens up Change Details Activity
            // You can change the Admin class details
            val intent = Intent(this, ChangeDetailsActivity::class.java)
            startActivity(intent)
        }

        transactionDetails.setOnClickListener {
            // When clicked Transaction Activity
            // You can see accounted details & unaccounted MPESAA details
            val intent = Intent(this, TransactionDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}