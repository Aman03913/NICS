package com.example.employerpage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.employerpage.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class signIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btntosignUp.setOnClickListener {
            val intent = Intent(applicationContext, signUp::class.java)
            startActivity(intent)
        }

        binding.btnsignIn.setOnClickListener {
            val sEmail = binding.userEmail.text.toString().trim()
            val sPassword = binding.userPassword.text.toString().trim()
            val jobTypeRadioGroup = binding.jobTypeRadioGroup
            val radioJobSeeker = binding.radioJobSeeker
            val radioJobProvider = binding.radioJobProvider

            val isJobSeeker = jobTypeRadioGroup.checkedRadioButtonId == radioJobSeeker.id
            val isJobProvider = jobTypeRadioGroup.checkedRadioButtonId == radioJobProvider.id

            if (sEmail.isNotEmpty() && sPassword.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(sEmail, sPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = if (isJobSeeker) {
                                Intent(applicationContext, seekerDataInput::class.java)
                            } else if (isJobProvider) {
                                Intent(applicationContext, postJob::class.java)
                            } else {
                                // Handle the case when neither Job Seeker nor Job Provider is selected.
                                // You can display a Toast or navigate to a default activity.
                                Toast.makeText(
                                    this,
                                    "Ab Select bhi m kru ki tu kon hai",
                                    Toast.LENGTH_LONG
                                ).show()
                                return@addOnCompleteListener
                            }
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "User to bnja mere Bhai", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Ye bhi M hi bhru kyu", Toast.LENGTH_LONG).show()
            }
        }
    }
}


