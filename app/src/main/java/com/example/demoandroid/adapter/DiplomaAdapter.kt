package com.example.demoandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoandroid.R

class DiplomaAdapter(private val onDeleteListener : (Int) -> Unit) : RecyclerView.Adapter<DiplomaAdapter.ViewHolder>() {
    private val diplomas : MutableList<String> = mutableListOf()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvDiploma : TextView = view.findViewById(R.id.tv_diploma_item_diploma)
        val btnDelete : Button = view.findViewById(R.id.btn_delete_item_diploma)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiplomaAdapter.ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_diploma, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiplomaAdapter.ViewHolder, position: Int) {
        val diploma = diplomas[position]
        holder.tvDiploma.text = diploma
        holder.btnDelete.setOnClickListener {
            onDeleteListener(position)
        }
    }

    override fun getItemCount(): Int {
        return diplomas.size
    }

    fun updateDiplomas(diplomas : List<String>) {
        this.diplomas.clear()
        this.diplomas.addAll(diplomas)
        notifyDataSetChanged()
    }
}