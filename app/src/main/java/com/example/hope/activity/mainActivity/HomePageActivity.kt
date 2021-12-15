package com.example.hope.activity.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.hope.R

//private lateinit var auth: FirebaseAuth
private lateinit var drawer_layout: DrawerLayout

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        drawer_layout = findViewById(R.id.drawer_layout)

        setSupportActionBar(findViewById(R.id.mainToolbar))

        val toogle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            findViewById(R.id.mainToolbar),
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toogle)
        toogle.syncState()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }
// buat logout
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val itemId = item.itemId
//
//        when (itemId) {
//            R.id.logout_toolbar -> {
//                val builder = AlertDialog.Builder(this)
//                val uid = auth.currentUser?.uid
//
//                builder.setMessage(R.string.logout_dialog)
//                    .setPositiveButton(R.string.yes) { _, _ ->
//                        logOut()
//                        Toast.makeText(this, R.string.logout_success, Toast.LENGTH_SHORT).show()
//                    }
//                    .setNegativeButton(R.string.no) { _, _ ->
//                        Log.d("Logout Button", "Logout success: $uid")
//                    }
//                    .show()
//            }
//        }
//        return false
//
//    }
}