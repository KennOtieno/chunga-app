import android.os.Bundle
import android.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.google.firebase.database.*

class EnrollmentSavingsActivity : AppCompatActivity() {

    private lateinit var studentsEnrolledTextView: TextView
    private lateinit var totalBalanceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enrollment_savings) // XML LAYOUT

        studentsEnrolledTextView = findViewById(R.id.studentsEnrolledTextView) // Assuming you have a TextView in XML layout with this ID
        totalBalanceTextView = findViewById(R.id.totalBalanceTextView) // Assuming you have a TextView in XML layout with this ID

        // Retrieve total students enrolled and total account balance from Firebase
        val studentsRef = FirebaseDatabase.getInstance().getReference("students")
        studentsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var totalStudents = 0
                var totalBalance = 0

                for (studentSnapshot in snapshot.children) {
                    val student = studentSnapshot.getValue(Students::class.java)
                    if (student != null) {
                        totalStudents++
                        totalBalance += student.accountBalance ?: 0
                    }
                }

                // Display total students and total balance
                totalStudentsTextView.text = "Students Enrolled: $totalStudents"
                totalBalanceTextView.text = "Total Account Balance: $totalBalance Ksh"
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error if needed
            }
        })
    }
}