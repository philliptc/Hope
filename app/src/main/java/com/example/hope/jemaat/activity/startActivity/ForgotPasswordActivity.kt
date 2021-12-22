package com.example.hope.jemaat.activity.startActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.hope.R
import com.example.hope.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

private lateinit var binding : ActivityForgotPasswordBinding
private lateinit var auth: FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.btnBackForgotPassword.setOnClickListener(this)
        binding.btnKirimForgotPassword.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){

            R.id.btnBack_ForgotPassword -> {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }

            R.id.btnKirim_ForgotPassword -> {
                val email : String = binding.etEmailForgotPassword.text.toString()
                if (TextUtils.isEmpty(email)) {
                    binding.etEmailForgotPassword.error = "Email tidak boleh kosong!"
                    return
                }
                sendPasswordResetEmail(email)
            }
        }
    }

    private fun sendPasswordResetEmail(email : String){
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                Toast.makeText(this, "Email sudah terkirim", Toast.LENGTH_SHORT).show()
                if (it.isSuccessful){
                    Log.d("FORGOT PASS" , "Link Forgot Password terkirim!")
                    val intent = Intent(this, EmailReceivedActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }


}