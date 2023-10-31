package com.example.employerpage

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Job

class JobPageforSeeker : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var jobArrayList: ArrayList<job>
    private lateinit var myAdapter: MyAdapterforSeeker
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_pagefor_seeker)

        recyclerView = findViewById(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        jobArrayList = arrayListOf()
        myAdapter = MyAdapterforSeeker(jobArrayList)
        recyclerView.adapter = myAdapter // Set the adapter to MyAdapterforSeeker

        db = FirebaseFirestore.getInstance()

        db.collection("Job Post")
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    for (document in querySnapshot.documents) {
                        val jobPost: job? = document.toObject(job::class.java)
                        if (jobPost != null) {
                            jobArrayList.add(jobPost)
                        }
                    }
                    myAdapter.notifyDataSetChanged() // Notify the adapter that the dataset has changed
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "GadBad Hogyi", Toast.LENGTH_LONG).show()
            }
    }
}
