package com.abumadi.sawaapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abumadi.sawaapp.data.models.PlacesModel
import com.abumadi.sawaapp.databinding.PlacesItemBinding

//TODO: let's convert this to Groupie
class PlacesAdapter(private val context: Context, private val placesItems: ArrayList<PlacesModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlacesViewHolder(
            PlacesItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = placesItems[position]
        if (holder is PlacesViewHolder) {
            holder.binding.tvPlaceName.text = model.placeName
            holder.binding.ivPlaceIcon.setImageResource(model.placeIcon ?: 0)
            holder.binding.tvPlaceFar.text = model.placeFar
            holder.binding.tvAvailabilityDescription.text = model.availabilityDescription
            holder.binding.tvCapacity.text = model.capacity
            holder.binding.tvCrowding.text = model.crowdingText
            holder.binding.llPlacesCrowding.setBackgroundResource(
                model.crowdingBackgroundColor ?: 0
            )
        }
    }

    override fun getItemCount(): Int {
        return placesItems.size
    }

    inner class PlacesViewHolder(val binding: PlacesItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}