package com.example.chunga_cash_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.chunga_cash_app.databinding.ActivityMainBinding


import com.google.firebase.database.ktx.values
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var studentDbRef: DatabaseReference
    private lateinit var binding: ActivityMainBinding
    private  var admissionNum: Int? = 0
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var circularProfileImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//            showSearchDialog()
//        }

            // Hooks
            navigationView = binding.navViews
            toolbar = binding.toolbar
            drawerLayout = binding.drawerLayout
            circularProfileImage = binding.circularProfileImage

            // Tool Bar
            setSupportActionBar(toolbar)
            navigationView.bringToFront()
            // Nav Bar
            val toggle = ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.nav_drawer_open,
                R.string.nav_drawer_close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

        toolbar.navigationIcon = circularProfileImage.drawable
        toolbar.setNavigationOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
//              Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
            }

                // Set a Click listener for the menu items if needed
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.main_menu, popupMenu.menu)
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
                    true
                }
                R.id.menu_administrator_account -> {
                    // When administrator account is selected, it opens AdministratorActivity
                    // It opens the AdministratorActivity when clicked
                    val  intent = Intent(this, AdministratorActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_settings -> {
                    // When settings is selected, it shows settings
                    // It opens settings activity when clicked
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }
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
                else -> false

            }
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
            val databaseReference = FirebaseDatabase.getInstance().getReference("Students")
            val query = databaseReference.child(admissionNumber)

            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {

                        // redirection to Search Activity
                        val intent = Intent(this@MainActivity, StartupActivity::class.java)
                        intent.putExtra("adminInput", admissionNumber)
                        showToast("Search for admission number: $admissionNumber")
                        startActivity(intent)
                    }
                    else {
                        showToast("Student does not exist")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    showToast("Database error")
                }
            })
        }
//            databaseReference.child(admissionNumber)

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//    when(item.itemId) {
//        R.id.nav_
//    }
//}
