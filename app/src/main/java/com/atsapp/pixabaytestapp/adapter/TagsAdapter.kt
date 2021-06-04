package com.atsapp.pixabaytestapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atsapp.pixabaytestapp.aux_interface.utils.AdapterOnClick
import com.atsapp.pixabaytestapp.data.models.Hit
import com.atsapp.pixabaytestapp.databinding.ItemTagBinding


//Clase para crear los TAGS
class TagsAdapter (private val items: List<String>, private val adapterOnClick: AdapterOnClick<Hit>? = null) : RecyclerView.Adapter<TagsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  = ViewHolder(
            ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position],position, adapterOnClick)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val itemBinding: ItemTagBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: String, position: Int, adapterOnClick: AdapterOnClick<Hit>? = null) {
            itemBinding.apply {
                tvTag.text = item
            }
        }
    }
}
