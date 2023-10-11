package com.example.employerpage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.employerpage.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class signUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root) // Use the inflated layout

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnsignUp.setOnClickListener {
            val sEmail = binding.userEmail.text.toString().trim()
            val sPassword = binding.userPassword.text.toString().trim()


            if (sEmail.isNotEmpty() && sPassword.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(sEmail, sPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "User Bangya hai bhai,Mze lo or Sign krlena Bhai", Toast.LENGTH_LONG).show()
                            val intent = Intent(applicationContext, signIn::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "User registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show()
            }
        }
    }
}
