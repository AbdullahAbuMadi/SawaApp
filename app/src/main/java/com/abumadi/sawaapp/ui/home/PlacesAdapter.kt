package com.abumadi.sawaapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.data.models.PlacesModel
import kotlinx.android.synthetic.main.places_item.view.*

class PlacesAdapter(private val context: Context, private val placesItems: ArrayList<PlacesModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlacesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.places_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = placesItems[position]
        if (holder is PlacesViewHolder) {
            holder.itemView.tv_place_name.text = model.placeName
            holder.itemView.iv_place_icon.setImageResource(model.placeIcon ?: 0)
            holder.itemView.tv_place_far.text = model.placeFar
            holder.itemView.tv_availability_description.text = model.availabilityDescription
            holder.itemView.tv_capacity.text = model.capacity
            holder.itemView.tv_crowding.text = model.crowdingText
            holder.itemView.ll_places_crowding.setBackgroundResource(model.crowdingBackgroundColor?:0)
        }
    }

    override fun getItemCount(): Int {
        return placesItems.size
    }

    inner class PlacesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}