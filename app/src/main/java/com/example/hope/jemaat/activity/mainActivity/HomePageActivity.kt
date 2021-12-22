package com.example.hope.jemaat.activity.mainActivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.hope.R
import com.example.hope.databinding.ActivityHomePageBinding
import com.example.hope.jemaat.activity.startActivity.SignInActivity
import com.example.hope.jemaat.fragment.JadwalPelayanFragment
import com.example.hope.jemaat.fragment.ProfileFragment
import com.example.hope.jemaat.fragment.QRScannerFragment
import com.example.hope.jemaat.fragment.RegistrasiIbadahFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

private lateinit var auth: FirebaseAuth
private lateinit var binding: ActivityHomePageBinding

class HomePageActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        setSupportActionBar(binding.mainToolbar)

        //buat replace fragment registrasi ke homepage
        val registrasiibadahfragment = RegistrasiIbadahFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, registrasiibadahfragment)
            .commit()

        val toogle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.mainToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        NavigationDrawer()

    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, fragment)
                .commit()
        }
        else{
            Log.e("Gagal Pindah", "ok")
        }
    }

// buat logout
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        when (itemId) {
            R.id.logout_toolbar -> {
                val builder = AlertDialog.Builder(this)
                val uid = auth.currentUser?.uid

                builder.setMessage(R.string.logout_dialog)
                    .setPositiveButton(R.string.yes) { _, _ ->
                        logOut()
                        Toast.makeText(this, R.string.logout_berhasil, Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton(R.string.no) { _, _ ->
                        Log.d("Logout Button", "Logout success: $uid")
                    }
                    .show()
            }
        }
        return false

    }

    private fun NavigationDrawer(){
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_registrasi -> {
                    Toast.makeText(this, "Halaman Registrasi Dipilih", Toast.LENGTH_SHORT).show()
                    val registrasiibadahfragment = RegistrasiIbadahFragment()
                    replaceFragment(registrasiibadahfragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_jadwalpelayan -> {
                    Toast.makeText(this, "Halaman Jadwal Pelayan Dipilih", Toast.LENGTH_SHORT).show()
                    val jadwalpelayanfragment = JadwalPelayanFragment()
                    replaceFragment(jadwalpelayanfragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_qrcode -> {
                    Toast.makeText(this, "Halaman Absensi Dipilih", Toast.LENGTH_SHORT).show()
                    val qrscannerfragment = QRScannerFragment()
                    replaceFragment(qrscannerfragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_profile -> {
                    Toast.makeText(this, "Halaman Profile Dipilih", Toast.LENGTH_SHORT).show()
                    val profilefragment = ProfileFragment()
                    replaceFragment(profilefragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        })
    }

    private fun logOut() {
        auth.signOut()
        intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}