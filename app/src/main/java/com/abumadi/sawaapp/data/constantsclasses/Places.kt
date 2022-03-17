package com.abumadi.sawaapp.data.constantsclasses

import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.data.models.PlacesModel


class Places {
    companion object {
        fun getPlacesList(): ArrayList<PlacesModel> {
            val placesList = ArrayList<PlacesModel>()
            placesList.add(
                PlacesModel(
                    R.drawable.carefour_icon,
                    "Carrefour City Mall",
                    "200 m away",
                    true,
                    " 8am–9pm",
                    " 142/250","Moderate", R.color.crowding_background,"Crowded"
                )
            )
            placesList.add(
                PlacesModel(
                    R.drawable.starbucks_icon,
                    "Starbucks City Mall",
                    "200 m away",
                    true,
                    " 8am–9pm",
                    " 38/40","Crowded", R.color.moderate_background,"Moderate"
                )
            )

            placesList.add(
                PlacesModel(
                    R.drawable.starbucks_icon,
                    "Starbucks City Mall",
                    "200 m away",
                    true,
                    " 8am–9pm",
                    " 38/40","Crowded", R.color.moderate_background,"Moderate"
                )
            )

            placesList.add(
                PlacesModel(
                    R.drawable.starbucks_icon,
                    "Starbucks City Mall",
                    "200 m away",
                    true,
                    " 8am–9pm",
                    " 38/40","Crowded", R.color.moderate_background,"Moderate"
                )
            )
            placesList.add(
                PlacesModel(
                    R.drawable.starbucks_icon,
                    "Starbucks City Mall",
                    "200 m away",
                    true,
                    " 8am–9pm",
                    " 38/40","Crowded", R.color.moderate_background,"Moderate"
                )
            )
            placesList.add(
                PlacesModel(
                    R.drawable.starbucks_icon,
                    "Starbucks City Mall",
                    "200 m away",
                    true,
                    " 8am–9pm",
                    " 38/40","Crowded", R.color.moderate_background,"Moderate"
                )
            )
            placesList.add(
                PlacesModel(
                    R.drawable.starbucks_icon,
                    "Starbucks City Mall",
                    "200 m away",
                    true,
                    " 8am–9pm",
                    " 38/40","Crowded", R.color.moderate_background,"Moderate"
                )
            )
            placesList.add(
                PlacesModel(
                    R.drawable.starbucks_icon,
                    "Starbucks City Mall",
                    "200 m away",
                    true,
                    " 8am–9pm",
                    " 38/40","Crowded", R.color.moderate_background,"Moderate"
                )
            )
            placesList.add(
                PlacesModel(
                    R.drawable.starbucks_icon,
                    "Starbucks City Mall",
                    "200 m away",
                    true,
                    " 8am–9pm",
                    " 38/40","Crowded", R.color.moderate_background,"Moderate"
                )
            )
            return placesList
        }
    }
}