package com.example.hope.activity.startActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.hope.R

class WelcomePageActivity : AppCompatActivity() {

    private val SPLASH_TIME : Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        Handler().postDelayed({
            startActivity(Intent (this, SignInActivity::class.java))
            finish()


        }, SPLASH_TIME)
    }
}