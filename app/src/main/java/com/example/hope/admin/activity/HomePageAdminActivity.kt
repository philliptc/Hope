package com.example.hope.admin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.hope.R
import com.example.hope.admin.activity.drawer_layout_admin

private lateinit var drawer_layout_admin: DrawerLayout

class HomePageAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_admin)

        drawer_layout_admin = findViewById(R.id.drawer_layout_admin)

        setSupportActionBar(findViewById(R.id.mainToolbar_admin))

        val toogle = ActionBarDrawerToggle(
            this,
            drawer_layout_admin,
            findViewById(R.id.mainToolbar_admin),
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout_admin.addDrawerListener(toogle)
        toogle.syncState()
    }

    override fun onBackPressed() {
        if (drawer_layout_admin.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_admin.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }
}