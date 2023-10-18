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

class jobsPage : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var jobArrayList: ArrayList<job>
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore

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


        db.collection("Job Post")
            .get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for(data in it.documents){
                        val  JobPost: job? =data.toObject(job::class.java)
                        if (JobPost != null) {
                            jobArrayList.add(JobPost)
                        }
                    }
                    recyclerView.adapter=MyAdapter(jobArrayList)
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "GadBad Hogyi", Toast.LENGTH_LONG).show()
            }
    }



//


}
