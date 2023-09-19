package com.example.touristapps

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.touristapps.databinding.ItemRowDestinationBinding

class ListDestinationAdapter(private val listDestination: ArrayList<Destination>) : RecyclerView.Adapter<ListDestinationAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemRowDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListDestinationAdapter.ListViewHolder, position: Int) {
        val destination = listDestination[position]
        Glide.with(holder.itemView.context)
            .load(destination.image)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = destination.name
        holder.binding.tvItemDescription.text = destination.description

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DESTINATION, destination)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listDestination.size

    class ListViewHolder(var binding: ItemRowDestinationBinding) : RecyclerView.ViewHolder(binding.root)

}