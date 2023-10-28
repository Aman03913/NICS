package com.example.employerpage

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class jobPostedByEmployer : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var jobArrayList: ArrayList<job>
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs_page)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        jobArrayList = arrayListOf()
        myAdapter = MyAdapter(jobArrayList)
        recyclerView.adapter = myAdapter

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val employerUID = auth.currentUser?.uid // Get the logged-in employer's UID

        if (employerUID != null) {
            db.collection("Job Post")
                .whereEqualTo("employerUID", employerUID) // Filter by employer UID
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        for (document in querySnapshot.documents) {
                            val jobPost: job? = document.toObject(job::class.java)
                            jobPost?.let {
                                jobArrayList.add(it)
                            }
                        }
                        myAdapter.notifyDataSetChanged()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Error occurred", Toast.LENGTH_LONG).show()
                }
        }
    }
}
