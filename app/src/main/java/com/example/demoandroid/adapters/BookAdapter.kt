package com.example.demoandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoandroid.R
import com.example.demoandroid.db.entities.Book

class BookAdapter : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private var books : List<Book> = listOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle : TextView = view.findViewById(R.id.tv_title_item_book)
        val tvDescription : TextView = view.findViewById(R.id.tv_description_item_book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_book, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        val book = books[position]
        holder.tvTitle.text = book.title
        holder.tvDescription.text = book.description
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun updateBooks(books : List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }
}