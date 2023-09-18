import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.circular_image_profile.*
import java.awt.PopupMenu

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.circular_image_profile) // set your custom layout

        // Replace abbreviation with the actual abbreviation you want to display
        abbreviation_text.text = "O.O.H.S"

        // Set click listener on the circular image profile
        profile_image.setOnClickListener { view ->
            showPopupMenu(view)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.your_dropdown_menu, popupMenu.menu)

        // Set a Click listener for the menu items if needed
        popupMenu.setOnMenuItemClickListener { item ->
            // Handle menu item click here
            when (item.itemId) {
                R.id.menu_search -> {
                    // When search is selected, it searches for student admission number
                    // It opens the search activity when clicked
                    val intent = Intent(this, SearchActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_register_student -> {
                    // When register student is selected, it adds student information
                    // It opens the add activity when clicked
                    val intent = Intent(this, AddActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_remove_student -> {
                    // When remove student is selected, it deletes student information
                    // It opens the delete activity when clicked
                    val intent = Intent(this, DeleteActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu_administrator_account -> {
                    // When administrator account is selected, it opens AdministratorActivity
                    // It opens the AdministratorActivity when clicked
                    val  intent = Intent(this, AdministratorActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu_settings -> {
                    // When settings is selected, it shows settings
                    // It opens settings activity when clicked
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu_log_out -> {
                    // When log out is selected, it signs out
                    // It opens the sign out activity when clicked
                    FirebaseAuth.getInstance().signOut
                    // Optionally you can navigate to sign im activity
                    val intent = Intent(this, SignOutActivity::class.java)
                    startActivity(intent)
                    true
                }
                // Add more menu items as needed
                else -> false

            }
        }

        popupMenu.show()
    }
}