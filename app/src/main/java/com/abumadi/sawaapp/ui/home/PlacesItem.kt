package com.abumadi.sawaapp.ui.home

import android.view.View
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.data.models.PlacesModel
import com.abumadi.sawaapp.databinding.PlacesItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class PlacesItem(private val places: PlacesModel) : BindableItem<PlacesItemBinding>() {
    override fun bind(binding: PlacesItemBinding, position: Int) {
        binding.apply {
            binding.tvPlaceName.text = places.placeName
            binding.ivPlaceIcon.setImageResource(places.placeIcon ?: 0)
            binding.tvPlaceFar.text = places.placeFar
            binding.tvAvailabilityDescription.text = places.availabilityDescription
            binding.tvCapacity.text = places.capacity
            binding.tvCrowding.text = places.crowdingText
            binding.llPlacesCrowding.setBackgroundResource(
                places.crowdingBackgroundColor ?: 0
            )
        }
    }

    override fun getLayout(): Int = R.layout.places_item

    override fun initializeViewBinding(view: View): PlacesItemBinding {
        return PlacesItemBinding.bind(view)
    }
}