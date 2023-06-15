package com.example.authfirebaseloginreg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class registration : AppCompatActivity() {
    lateinit var text_gotologin:TextView
    lateinit var EDtregemail:EditText
    lateinit var Edtregpass:EditText
    lateinit var Edtconfirmpass:EditText
    lateinit var Btn_register:Button
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        text_gotologin=findViewById(R.id.textView)
        EDtregemail=findViewById(R.id.Edtlogemail_regemail)
        Edtregpass=findViewById(R.id.Edt_1stpass)
        Edtconfirmpass=findViewById(R.id.Edt_2ndpass)
        Btn_register=findViewById(R.id.Btn_signup)
        auth=Firebase.auth

        text_gotologin.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        Btn_register.setOnClickListener {
            Signupuser()
        }
    }
    private fun Signupuser(){
        val email=EDtregemail.text.toString()
        val pass=Edtregpass.text.toString()
        val confirmpass=Edtconfirmpass.text.toString()

        if (email.isBlank()||pass.isBlank()||confirmpass.isBlank()){
            Toast.makeText(this,"Please password and email can't be blank", Toast.LENGTH_LONG )
            return
        }else if (pass!=confirmpass){
            Toast.makeText(this, "Password do not match", Toast.LENGTH_LONG).show()
        }
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this) {
            if (it.isSuccessful){
                Toast.makeText(this, "Signed up successfully", Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this, "Failed to create user", Toast.LENGTH_LONG).show()
            }
        }

    }
}