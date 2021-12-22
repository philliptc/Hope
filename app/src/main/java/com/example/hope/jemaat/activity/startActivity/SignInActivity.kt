package com.example.hope.jemaat.activity.startActivity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.*
import com.example.hope.R
import com.example.hope.jemaat.activity.mainActivity.HomePageActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var progressDialog : ProgressDialog

private lateinit var etEmail_SignIn : EditText
private lateinit var etPassword_SignIn : EditText
private lateinit var tvForgotPassword_SignIn : TextView
private lateinit var btnEyes_SignIn : ImageButton
private lateinit var btnMasuk_SignIn : Button
private lateinit var btnBuatAkun_SignIn : Button
private lateinit var auth : FirebaseAuth
private var clicked = true

class SignInActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        etEmail_SignIn = findViewById(R.id.etEmail_SignIn)
        etPassword_SignIn = findViewById(R.id.etPassword_SignIn)
        tvForgotPassword_SignIn = findViewById(R.id.tvForgotPassword_SignIn)
        btnMasuk_SignIn = findViewById(R.id.btnMasuk_SignIn)
        btnBuatAkun_SignIn = findViewById(R.id.btnBuatAkun_SignIn)
        btnEyes_SignIn = findViewById(R.id.btnEyes_SignIn)

        auth = FirebaseAuth.getInstance()

        btnMasuk_SignIn.setOnClickListener(this)
        btnBuatAkun_SignIn.setOnClickListener(this)
        btnEyes_SignIn.setOnClickListener(this)
        tvForgotPassword_SignIn.setOnClickListener(this)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Silahkan Tunggu")
        progressDialog.setMessage("Sedang Login...")
        progressDialog.setCanceledOnTouchOutside(false)

    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.btnMasuk_SignIn -> {
                val email : String = etEmail_SignIn.text.toString()
                val password : String = etPassword_SignIn.text.toString()

                if (TextUtils.isEmpty(email)){
                    etEmail_SignIn.error = "Email harus diisi!"
                    return
                }
                if(TextUtils.isEmpty(password)){
                    etPassword_SignIn.error = "Password harus diisi!"
                    return
                }
                if (password.length < 6) {
                    etPassword_SignIn.error = "Password wajib minimal 6 karakter!"
                    return
                }
                signIn(email,password)
            }

            R.id.btnBuatAkun_SignIn -> {
                startActivity(Intent(this, SignUpActivity::class.java))
//                finish() //biar kalo back ga balik ke sign in lagi
            }

            R.id.tvForgotPassword_SignIn -> {
                startActivity(Intent(this, ForgotPasswordActivity::class.java))
                finish()
            }

            R.id.btnEyes_SignIn -> {

                if (clicked){
                    etPassword_SignIn.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    btnEyes_SignIn.setBackgroundResource(R.drawable.ic_closedeye)
                    clicked = false

                }
                else {
                    etPassword_SignIn.transformationMethod = PasswordTransformationMethod.getInstance()
                    btnEyes_SignIn.setBackgroundResource(R.drawable.ic_eye)
                    clicked = true
                }
            }
        }
    }

    //kalo udh login, langsung masuk aplikasi
    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            startActivity(Intent(this, HomePageActivity::class.java))
        }
    }

    private fun signIn(email: String, password: String) {
        progressDialog.show()
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Toast.makeText(this, "Login berhasil", Toast.LENGTH_LONG).show()
                Log.d("LOGIN", "signInWithEmail:success")
                progressDialog.dismiss()
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Login gagal | ${e.message}", Toast.LENGTH_LONG).show()
                Log.d("LOGIN", "signInWithEmail:failed")
                progressDialog.dismiss()
            }
    }


}

