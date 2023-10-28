package com.example.employerpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class postJob : AppCompatActivity() {
    private lateinit var jobTitle: EditText
    private lateinit var companyName: EditText
    private lateinit var jD: EditText
    private lateinit var quali: EditText
    private lateinit var loc: EditText
    private lateinit var salary: EditText
    private lateinit var btn: Button

    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.postjobs)

        jobTitle = findViewById(R.id.jobTitleEditText)
        companyName = findViewById(R.id.companyNameEditText)
        jD = findViewById(R.id.jobDescriptionEditText)
        quali = findViewById(R.id.qualificationsEditText)
        loc = findViewById(R.id.locationEditText)
        salary = findViewById(R.id.salaryEditText)
        btn = findViewById(R.id.submitButton)

        btn.setOnClickListener {
            val jTitle = jobTitle.text.toString().trim()
            val cName = companyName.text.toString().trim()
            val JD = jD.text.toString().trim()
            val QUALI = quali.text.toString().trim()
            val LOC = loc.text.toString().trim()
            val SAL = salary.text.toString().trim()

            val employerUID = auth.currentUser?.uid // Get the employer's UID

            val jobPost = hashMapOf(
                "jobTitle" to jTitle,
                "companyName" to cName,
                "JobDescription" to JD,
                "Qualification" to QUALI,
                "Location" to LOC,
                "Salary" to SAL,
                "employerUID" to employerUID // Include the employer's UID
            )

            db.collection("Job Post").document().set(jobPost)
                .addOnSuccessListener {
                    Toast.makeText(this, "Job Added Successfully", Toast.LENGTH_SHORT).show()
                    jobTitle.text.clear()
                    companyName.text.clear()
                    jD.text.clear()
                    quali.text.clear()
                    loc.text.clear()
                    salary.text.clear()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Job is Not Posted", Toast.LENGTH_SHORT).show()
                }

            val intent = Intent(applicationContext, jobPostedByEmployer::class.java)
            startActivity(intent)
        }

        val skipJobPostClick = findViewById<TextView>(R.id.skipJobPost)
        skipJobPostClick.setOnClickListener {
            val i = Intent(this, jobPostedByEmployer::class.java)
            startActivity(i)
        }
    }
}
