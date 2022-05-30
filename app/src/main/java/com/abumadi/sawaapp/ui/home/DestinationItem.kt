package com.abumadi.sawaapp.ui.home

import android.view.View
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.data.models.DestinationModel
import com.abumadi.sawaapp.databinding.DestinationItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class DestinationItem(private val destination: DestinationModel) :
    BindableItem<DestinationItemBinding>() {
    override fun bind(binding: DestinationItemBinding, position: Int) {
        binding.apply {
            binding.llDestination.setBackgroundResource(destination.destinationBackgroundColor)
            binding.ivDestinationIcon.setImageResource(destination.destinationLogo ?: 0)
            binding.tvDestinationDescription.text =
                destination.destinationDescription
        }
    }

    override fun getLayout(): Int = R.layout.destination_item

    override fun initializeViewBinding(view: View): DestinationItemBinding {
        return DestinationItemBinding.bind(view)
    }
}