package com.example.employerpage

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class seekerAdapter(private val seekerList: ArrayList<Seeker>) : RecyclerView.Adapter<seekerAdapter.MyViewHolderSeeker>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderSeeker {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.seeker_item, parent, false)
        return MyViewHolderSeeker(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolderSeeker, position: Int) {
        val seeker: Seeker = seekerList[position]
        holder.fullNameSeeker.text = seeker.fullname
        holder.jobTitleSeeker.text = seeker.jobTitle
        holder.locationSeeker.text = seeker.location
        holder.experienceSeeker.text = seeker.experience



    }

    override fun getItemCount(): Int {
        return seekerList.size
    }

    public class MyViewHolderSeeker(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullNameSeeker: TextView = itemView.findViewById(R.id.tvfullName)
        val jobTitleSeeker: TextView = itemView.findViewById(R.id.tvjobTitle)
        val locationSeeker: TextView = itemView.findViewById(R.id.tvlocation)
        val experienceSeeker: TextView = itemView.findViewById(R.id.tvExperience)


    }
}