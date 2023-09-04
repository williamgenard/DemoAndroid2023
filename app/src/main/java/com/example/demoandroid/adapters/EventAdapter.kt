package com.example.demoandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoandroid.R

class EventAdapter : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    private val events : MutableList<String> = mutableListOf()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvEvent : TextView = view.findViewById(R.id.tv_item_event)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.ViewHolder {
        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventAdapter.ViewHolder, position: Int) {
        val event = events[position]
        holder.tvEvent.text = event
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun updateEvents(events: List<String>) {
        this.events.clear()
        this.events.addAll(events)
        notifyDataSetChanged()
    }
}