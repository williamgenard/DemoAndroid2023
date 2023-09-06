package com.example.demoandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoandroid.R
import com.example.demoandroid.models.Product
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductAdapter(private val productEventListener: ProductEventListener) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    interface ProductEventListener {
        fun onDelete(position : Int)
        fun onEdit(product : Product, position : Int)
    }

    private var products : List<Product> = mutableListOf()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvProduct : TextView = view.findViewById(R.id.tv_product_name_item_product)
        val btnDelete : FloatingActionButton = view.findViewById(R.id.btn_delete_product_item_product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val product = products[position]
        val name = "${product.name} : ${product.quantity}"
        holder.tvProduct.text = name
        holder.btnDelete.setOnClickListener {
            productEventListener.onDelete(position)
        }
        holder.itemView.setOnClickListener {
            productEventListener.onEdit(product, position)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateProducts(products : List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }
}