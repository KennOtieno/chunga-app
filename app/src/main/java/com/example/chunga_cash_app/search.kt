import android.os.Bundle
import androidx.appcompact.app.AlertDialog
import androidx.appcompact.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import Kotlinx.android.synthetic.main.activity_main.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton.setOnClickListener {
            showSearchDialog()
        }
    }

    private fun showSearchDialog() {
        val builder = AlertDialog.Builder
        builder.SetTitle //search for admission number
    }
}