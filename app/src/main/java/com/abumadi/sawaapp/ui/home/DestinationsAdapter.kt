package com.abumadi.sawaapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.data.models.DestinationModel
import kotlinx.android.synthetic.main.destination_item.view.*

class DestinationsAdapter(
    private val context: Context,
    private val destinationItems: ArrayList<DestinationModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.destination_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = destinationItems[position]
        if (holder is MyViewHolder) {
            holder.itemView.ll_destination.setBackgroundResource(model.destinationBackgroundColor)
            holder.itemView.iv_destination_icon.setImageResource(model.destinationLogo ?: 0)
            holder.itemView.tv_destination_description.text =
                model.destinationDescription
        }
    }

    override fun getItemCount(): Int {
        return destinationItems.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}