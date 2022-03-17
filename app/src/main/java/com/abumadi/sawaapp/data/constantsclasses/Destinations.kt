package com.abumadi.sawaapp.data.constantsclasses

import android.content.Context
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.data.models.DestinationModel

class Destinations {

    companion object {
        fun getDestinationsList(context: Context): ArrayList<DestinationModel> {
            val destinationsList = ArrayList<DestinationModel>()
            destinationsList.add(
                DestinationModel(
                    R.drawable.all_destination_icon,
                    context.getString(R.string.all_dest),
                    R.color.all_destination_background
                )
            )
            destinationsList.add(
                DestinationModel(
                    R.drawable.ic_retail_icon,
                    context.getString(R.string.retail_dest),
                    R.color.retail_destination_background
                )
            )
            destinationsList.add(
                DestinationModel(
                    R.drawable.ic_health_icon,
                    context.getString(R.string.health_dest),
                    R.color.health_destination_background
                )
            )
            destinationsList.add(
                DestinationModel(
                    R.drawable.ic_banks_icon,
                    context.getString(R.string.banks_dest),
                    R.color.banks_destination_background
                )
            )
            destinationsList.add(
                DestinationModel(
                    R.drawable.ic_government_icon,
                    context.getString(R.string.government_dest),
                    R.color.government_destination_background
                )
            )
            destinationsList.add(
                DestinationModel(
                    R.drawable.ic_entertainment_icon,
                    context.getString(R.string.entertaiment_dest),
                    R.color.entertain_destination_background
                )
            )
            destinationsList.add(
                DestinationModel(
                    R.drawable.ic_telecominucation_icon,
                    context.getString(R.string.telecommunication_dest),
                    R.color.telecom_destination_background
                )
            )
            destinationsList.add(
                DestinationModel(
                    R.drawable.ic_education_icon,
                    context.getString(R.string.education_dest),
                    R.color.education_destination_background
                )
            )
            destinationsList.add(
                DestinationModel(
                    R.drawable.ic_travle_icon,
                    context.getString(R.string.travel_dest),
                    R.color.travel_destination_background
                )
            )
            destinationsList.add(
                DestinationModel(
                    R.drawable.ic_insurance_icon,
                    context.getString(R.string.insurance_dest),
                    R.color.insurance_destination_background
                )
            )
            return destinationsList
        }
    }
}