package com.example.hope.jemaat.activity.startActivity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.hope.R
import com.example.hope.jemaat.fragment.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var progressDialog : ProgressDialog

private lateinit var etNamaLengkap_SignUp : EditText
private lateinit var etEmail_SignUp : EditText
private lateinit var etPassword_SignUp : EditText
private lateinit var btnBuatAkun_SignUp : Button
private lateinit var btnEyes_SignUp : ImageButton
private lateinit var auth : FirebaseAuth
private lateinit var database : FirebaseFirestore
private var clicked = true

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etNamaLengkap_SignUp = findViewById(R.id.etNamaLengkap_SignUp)
        etEmail_SignUp = findViewById(R.id.etEmail_SignUp)
        etPassword_SignUp = findViewById(R.id.etPassword_SignUp)
        btnBuatAkun_SignUp = findViewById(R.id.btnBuatAkun_SignUp)
        btnEyes_SignUp = findViewById(R.id.btnEyes_SignUp)

        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Silahkan Tunggu")
        progressDialog.setMessage("Sedang Melakukan Pendaftaran...")
        progressDialog.setCanceledOnTouchOutside(false)

        btnEyes_SignUp.setOnClickListener{
            val background = btnEyes_SignUp.background
            if (clicked){
                etPassword_SignUp.transformationMethod = HideReturnsTransformationMethod.getInstance()
                btnEyes_SignUp.setBackgroundResource(R.drawable.ic_closedeye)
                clicked = false

            }
            else {
                etPassword_SignUp.transformationMethod = PasswordTransformationMethod.getInstance()
                btnEyes_SignUp.setBackgroundResource(R.drawable.ic_eye)
                clicked = true
            }
        }

        btnBuatAkun_SignUp.setOnClickListener {
            val fullname : String = etNamaLengkap_SignUp.text.toString()
            val email : String = etEmail_SignUp.text.toString()
            val password : String = etPassword_SignUp.text.toString()

            if(TextUtils.isEmpty(fullname)){
                etNamaLengkap_SignUp.error = "Nama harus diisi!"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(email)){
                etEmail_SignUp.error = "Email harus diisi!"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password)){
                etPassword_SignUp.error = "Password harus diisi!"
                return@setOnClickListener
            }
            if (password.length < 6){
                etPassword_SignUp.error = "Password wajib minimal 6 karakter!"
                return@setOnClickListener
            }
            if (!isEmailValid(email)){
                etEmail_SignUp.error = "Email tidak valid!"
                return@setOnClickListener
            }
            signUp(fullname, email, password)
        }
    }

    private fun signUp(fullname: String, email: String, password: String) {
        progressDialog.show()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val uid = auth.currentUser?.uid.toString()
                val user = Users(uid, fullname, email)
                database.collection("users").document(uid).set(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Pendaftaran Berhasil!", Toast.LENGTH_LONG).show()
                        progressDialog.dismiss()
                        startActivity(Intent(this, SignInActivity::class.java))
                        finish()
                    }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Pendaftaran gagal!", Toast.LENGTH_LONG).show()
                Log.e("SIGN UP", "Pendaftaran gagal!", e)
                progressDialog.dismiss()
            }
    }
    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}