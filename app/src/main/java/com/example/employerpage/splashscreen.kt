package com.example.employerpage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.google.firebase.storage.StorageReference
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        Handler().postDelayed({
            val intent = Intent(applicationContext, signIn::class.java)
            startActivity(intent)
        },3000)

}
}


