package com.atsapp.pixabaytestapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.atsapp.pixabaytestapp.R
import com.atsapp.pixabaytestapp.aux_interface.utils.AdapterOnClick
import com.atsapp.pixabaytestapp.data.models.Hit
import com.atsapp.pixabaytestapp.databinding.ItemPhotoBinding
import com.bumptech.glide.Glide

//Clase para inflar las imagenes
class PictureAdapter (private val items: List<Hit>, private val adapterOnClick: AdapterOnClick<Hit>? = null) : RecyclerView.Adapter<PictureAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  = ViewHolder(
        ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position],position, adapterOnClick)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val itemBinding: ItemPhotoBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Hit, position: Int, adapterOnClick: AdapterOnClick<Hit>? = null) {
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.setColorSchemeColors(itemView.context.resources.getColor(R.color.seconndary_color))
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            itemBinding.apply {
                tvTitle.text = item.user
                Glide.with(itemBinding.root)
                    .load(item.largeImageURL)
                    .error(itemView.context.getDrawable(R.drawable.pixbay_logo))
                    .placeholder(circularProgressDrawable)
                    .into(ivPicture)
                cvItem.setOnClickListener {
                    adapterOnClick!!.adapterOnClick(item)
                }

            }

        }
    }
}
