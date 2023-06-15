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

class MainActivity : AppCompatActivity() {
    lateinit var Txt_gotoregister:TextView
    lateinit var Edtlogemail:EditText
    lateinit var Edtlogpass:EditText
    lateinit var Btnlogin:Button
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Txt_gotoregister=findViewById(R.id.TVlogin)
        Edtlogemail=findViewById(R.id.Edtlogemail)
        Edtlogpass=findViewById(R.id.Edtlogpass)
        Btnlogin=findViewById(R.id.Btn_login)
        auth= FirebaseAuth.getInstance()
        
        Txt_gotoregister.setOnClickListener{
            val intent=Intent(this, registration::class.java)
            startActivity(intent)
        }
        Btnlogin.setOnClickListener {
            Login()
        }

    }
    private fun Login(){
        val email=Edtlogemail.text.toString()
        val pass=Edtlogpass.text.toString()
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this, "Successfully Logged in", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Log in Failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}