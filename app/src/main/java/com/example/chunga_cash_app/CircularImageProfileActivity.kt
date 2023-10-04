package com.example.chunga_cash_app

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.chunga_cash_app.databinding.ActivityMainBinding
//import kotlinx.android.synthetic.main.circular_image_profile.*
//import java.awt.PopupMenu

class CircularImageProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // set your custom layout (view binding)

        // Replace abbreviation with the actual abbreviation you want to display
        val abbreviationText = "OOHS"
//        abbreviation_text.text = "O.O.H.S"
        val bitmapWidth = 75
        val bitmapHeight = 90

        val bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.color = Color.BLACK
        paint.textSize = 24f
        paint.isAntiAlias = true
        paint.textAlign = Paint.Align.CENTER

        val xPos = canvas.width / 2
        val yPos = (canvas.height / 2 - (paint.descent() + paint.ascent()) / 2)

        canvas.drawText(abbreviationText, xPos.toFloat(), yPos, paint)

        val circularProfileImageView = binding.circularProfileImage
        circularProfileImageView.setImageBitmap(bitmap)
//
//
//        Set click listener on the circular image profile
//        binding.toolbar.setOnClickListener{ view ->
//            showPopupMenu(view)
//        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.main_menu, popupMenu.menu)
//
//        // Set a Click listener for the menu items if needed
//        popupMenu.setOnMenuItemClickListener { item ->
//            // Handle menu item click here
//            when (item.itemId) {
//                R.id.menu_search -> {
//                    // When search is selected, it searches for student admission number
//                    // It opens the search activity when clicked
//                    val intent = Intent(this, SearchActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.menu_register_student -> {
//                    // When register student is selected, it adds student information
//                    // It opens the add activity when clicked
//                    val intent = Intent(this, AddActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.menu_remove_student -> {
//                    // When remove student is selected, it deletes student information
//                    // It opens the delete activity when clicked
//                    val intent = Intent(this, com.example.chunga_cash_app.DeleteActivity::class.java)
//                    startActivity(intent)
//                }
//                R.id.menu_administrator_account -> {
//                    // When administrator account is selected, it opens AdministratorActivity
//                    // It opens the AdministratorActivity when clicked
//                    val  intent = Intent(this, AdministratorActivity::class.java)
//                    startActivity(intent)
//                }
//                R.id.menu_settings -> {
//                    // When settings is selected, it shows settings
//                    // It opens settings activity when clicked
//                    val intent = Intent(this, SettingsActivity::class.java)
//                    startActivity(intent)
//                }
//                R.id.menu_log_out -> {
//                    // When log out is selected, it signs out
//                    // It opens the sign out activity when clicked
//                    FirebaseAuth.getInstance().signOut
//                    // Optionally you can navigate to sign im activity
//                    val intent = Intent(this, SignOutActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
//                // Add more menu items as needed
//                else -> false
//
//            }
//        }

        popupMenu.show()
    }
}