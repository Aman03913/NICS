package com.example.employerpage

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.auth.User

class MyAdapter(private val jobList: ArrayList<job>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.job_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvtitle.text=jobList[position].jobTitle
        holder.tvcompanyName.text=jobList[position].companyName
        holder.tvloc.text=jobList[position].Location
        holder.tvtitle.text = jobList[position].jobTitle
        // Add OnClickListener to handle item clicks
        holder.itemView.setOnClickListener {
            // Create an Intent to start the jobDetail activity
            val intent = Intent(holder.itemView.context, jobDetail::class.java)
            // Pass job details as extras in the Intent
            intent.putExtra("jobTitle", jobList[position].jobTitle)
            intent.putExtra("companyName", jobList[position].companyName)
            intent.putExtra("Location", jobList[position].Location)
            intent.putExtra("Qualifiaction", jobList[position].Qualifiaction)
            intent.putExtra("Salary", jobList[position].Salary)
            intent.putExtra("JobDesciption", jobList[position].JobDesciption)


            // Add more details as needed
            // Start the activity
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvtitle: TextView = itemView.findViewById(R.id.jobTitle)
        val tvcompanyName: TextView = itemView.findViewById(R.id.companyName)
        val tvloc: TextView = itemView.findViewById(R.id.location)

    }
}

