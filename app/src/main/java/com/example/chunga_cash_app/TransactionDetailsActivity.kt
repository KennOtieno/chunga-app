import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.appcompat.app.AppCompatActivity

class TransactionDetailsActivity : AppCompatActivity() {
    override fun  onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_details) // XML LAYOUT DON'T FORGET

        val accountedMessages = findViewById<Button>(R.id.accountedMessages)
        val unaccountedMessages = findViewById<Button>(R.id.unaccountedMessages)

        accountedMessages.setOnClickListener {
            // When clicked, it navigates to AccountedDetailsActivity
            // Here you can see confirmation MPESA messages from MPESA numbers that were saved by students while enrolling in the app.
            val intent = Intent(this, accountedMessagesActivity::class.java)
            startActivity(intent)
        }

        unaccountedMessages.setOnClickListener {
            // When clicked it navigates to UnaccountedDetailsActivity
            // Here you can see confirmation MPESA messages from MPESA numbers that aren't saved by students
            val intent = Intent(this, unaccountedMessagesActivity::class.java)
            startActivity(intent)
        }
    }
}