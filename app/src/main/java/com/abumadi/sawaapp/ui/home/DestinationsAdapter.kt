package com.abumadi.sawaapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abumadi.sawaapp.data.models.DestinationModel
import com.abumadi.sawaapp.databinding.DestinationItemBinding

class DestinationsAdapter(
    private val context: Context,
    private val destinationItems: ArrayList<DestinationModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DestinationItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = destinationItems[position]
        if (holder is MyViewHolder) {
            holder.binding.llDestination.setBackgroundResource(model.destinationBackgroundColor)
            holder.binding.ivDestinationIcon.setImageResource(model.destinationLogo ?: 0)
            holder.binding.tvDestinationDescription.text =
                model.destinationDescription
        }
    }

    override fun getItemCount(): Int {
        return destinationItems.size
    }

    inner class MyViewHolder(val binding: DestinationItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}