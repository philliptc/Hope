package com.example.hope.jemaat.activity.startActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.hope.R
import com.example.hope.databinding.ActivityEmailReceivedBinding
import com.google.firebase.auth.FirebaseAuth

private lateinit var binding : ActivityEmailReceivedBinding
private lateinit var auth : FirebaseAuth


class EmailReceivedActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailReceivedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.btnBackEmailReceived.setOnClickListener(this)
        binding.btnSelesaiEmailReceived.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.btnBack_EmailReceived -> {
                startActivity(Intent(this, ForgotPasswordActivity::class.java))
                finish()
            }

            R.id.btnSelesai_EmailReceived -> {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }
    }
}