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

            val isJobSeeker = jobTypeRadioGroup.checkedRadioButtonId == radioJobSeeker.id// True False
            if (sEmail.isNotEmpty() && sPassword.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = if (isJobSeeker) {
                            Intent(applicationContext, seekerAbout::class.java)
                        } else {
                            Intent(applicationContext, postJob::class.java)
                        }
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Are Baba user hi create nhi hua", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Abe email or password m Bharne aau kya", Toast.LENGTH_LONG).show()
            }
        }
    }
}
