package com.example.employerpage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
//import com.github.barteksc.pdfviewer.PDFView;


class seekerDetailsPage : AppCompatActivity() {

    private lateinit var recyclerView1: RecyclerView
    private lateinit var seekerArrayList: ArrayList<Seeker>
    private lateinit var myAdapter1: seekerAdapter
    private lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seeker_details_page) // Make sure to set the content view.

        recyclerView1 = findViewById(R.id.recyclerView1)
        recyclerView1.layoutManager = LinearLayoutManager(this)
        recyclerView1.setHasFixedSize(true)

        seekerArrayList = arrayListOf()
        myAdapter1 = seekerAdapter(seekerArrayList)
        recyclerView1.adapter = myAdapter1

        database = FirebaseFirestore.getInstance()

        database.collection("seekerDetails")
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    for (document in querySnapshot.documents) {
                        val seeker: Seeker? = document.toObject(Seeker::class.java)
                        seeker?.let {
                            seekerArrayList.add(it)
                        }
                    }
                    myAdapter1.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { exception ->
                runOnUiThread {
                    Toast.makeText(this, "GadBad Hogyi", Toast.LENGTH_LONG).show()
                }
            }
    }

}