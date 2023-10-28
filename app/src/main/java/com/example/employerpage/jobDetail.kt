package com.example.employerpage

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.employerpage.databinding.ActivityJobDetailBinding

class jobDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_detail)
        // Extract job details from the Intent extras
        val jobTitle = intent.getStringExtra("jobTitle") ?: "Default Title"
        val companyName = intent.getStringExtra("companyName") ?: "Default Company"
        val jobDescription=intent.getStringExtra("JobDesciption") ?: "Default Company"
        val salary=intent.getStringExtra("Salary") ?: "Default Company"
        val location=intent.getStringExtra("Location") ?: "Default Company"
        val qualification=intent.getStringExtra("Qualifiaction")?: "Default Company"
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



        }
    }

