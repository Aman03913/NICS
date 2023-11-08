package com.example.employerpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class JobDetailsforSeeker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_detailsfor_seeker)

        // Retrieve job details from the intent
        val jobTitle = intent.getStringExtra("jobTitle") ?: "Default Title"
        val companyName = intent.getStringExtra("companyName") ?: "Default Company"
        val jobDescription=intent.getStringExtra("JobDescription") ?: "Default Company"
        val salary=intent.getStringExtra("Salary") ?: "Default Company"
        val location=intent.getStringExtra("Location") ?: "Default Company"
        val qualification=intent.getStringExtra("Qualification")?: "Default Company"
        // Extract more details as needed

        // Populate TextViews with job details
        val titleTextView = findViewById<TextView>(R.id.Details_jobTitle)
        titleTextView.text = jobTitle

        val companyNameTextView = findViewById<TextView>(R.id.details_companyName)
        companyNameTextView.text = companyName

        val jobDescriptionTextView = findViewById<TextView>(R.id.details_Description)
        jobDescriptionTextView.text = jobDescription

        val salaryTextView = findViewById<TextView>(R.id.details_Salary)
        salaryTextView.text = salary

        val locationTextView = findViewById<TextView>(R.id.details_Location)
        locationTextView.text = location

        val qualificationTextView = findViewById<TextView>(R.id.details_Qualification)
        qualificationTextView.text = qualification

        val applyButton = findViewById<Button>(R.id.applyBtn)

        applyButton.setOnClickListener {
            val jobTitleSeeker = intent.getStringExtra("jobTitle") ?: "Default Title"
            val fullNameSeeker = intent.getStringExtra("fullname") ?: "Default Full Name"
            val locationSeeker = intent.getStringExtra("location") ?: "Default Location"
            val experienceSeeker = intent.getStringExtra("experience") ?: "Default Experience"

            // Create a Firestore reference to the collection where you want to store the job application data
            val db = FirebaseFirestore.getInstance()
            val jobApplicationsCollection = db.collection("jobApplications")

            // Create a map with the job application data
            val jobApplicationData = hashMapOf(
                "fullname" to fullNameSeeker,
                "jobTitle" to jobTitleSeeker,
                "location" to locationSeeker,
                "experience" to experienceSeeker
            )



            // Add the job application data to Firestore
            jobApplicationsCollection.add(jobApplicationData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Job Application Submitted Successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to submit job application", Toast.LENGTH_SHORT).show()
                }
            Log.d("JobDetailsforSeeker", "Saving job application data: $jobApplicationData")
        }
    }
}
