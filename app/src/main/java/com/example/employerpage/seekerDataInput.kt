package com.example.employerpage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.employerpage.databinding.ActivitySeekerDataInputBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class seekerDataInput : AppCompatActivity() {

    private val db = Firebase.firestore
    private lateinit var binding: ActivitySeekerDataInputBinding
    lateinit var mStorage: StorageReference
    private val PICK_PDF_REQUEST = 1
    lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mStorage = FirebaseStorage.getInstance().getReference("Uploads")
        binding = ActivitySeekerDataInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            val fullNameSeeker = binding.nameEditText.text.toString().trim()
            val jobTitleSeeker = binding.jobTitleEditText.text.toString().trim()
            val locationSeeker = binding.locationEditText.text.toString().trim()
            val experienceSeeker = binding.experienceEditText.text.toString().trim()
            val intent = Intent(this, JobDetailsforSeeker::class.java)

            // Add the data as extras to the Intent
            intent.putExtra("fullname", fullNameSeeker)
            intent.putExtra("jobTitle", jobTitleSeeker)
            intent.putExtra("location", locationSeeker)
            intent.putExtra("experience", experienceSeeker)
            

            val seekerMap = hashMapOf(
                "fullname" to fullNameSeeker,
                "jobTitle" to jobTitleSeeker,
                "location" to locationSeeker,
                "experience" to experienceSeeker
            )

            db.collection("seekerDetails").add(seekerMap)
                .addOnSuccessListener {
                    Toast.makeText(this, "Job Seeker Added Successfully", Toast.LENGTH_SHORT).show()
                    binding.nameEditText.text.clear()
                    binding.jobTitleEditText.text.clear()
                    binding.locationEditText.text.clear()
                    binding.experienceEditText.text.clear()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to add job seeker", Toast.LENGTH_SHORT).show()
                }
        }

        val pickPdfButton = findViewById<Button>(R.id.chooseResumeButton)
        pickPdfButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            startActivityForResult(intent, PICK_PDF_REQUEST)
        }

        binding.skipJobPost.setOnClickListener {
            val i = Intent(applicationContext, JobPageforSeeker::class.java)
            startActivity(i)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK) {
            uri = data?.data!!
            upload()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun upload() {
        var mReference = uri.lastPathSegment?.let { mStorage.child(it) }
        try {
            if (mReference != null) {
                mReference.putFile(uri)
                    .addOnSuccessListener { taskSnapshot ->
                        mReference.downloadUrl.addOnSuccessListener(OnSuccessListener { uri ->
                            val url = uri.toString()
                            val dwnTxt = findViewById<TextView>(R.id.selectedResumeTextView)
                            dwnTxt.text = url
                            Toast.makeText(this, "Uploaded Successfully. URL: $url", Toast.LENGTH_SHORT).show()
                        })
                    }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Thik se upload kr bhai", Toast.LENGTH_SHORT).show()
        }
    }
}
