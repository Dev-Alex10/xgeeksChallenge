package com.example.xgeekschallenge

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.xgeekschallenge.data.model.Photo
import com.google.gson.Gson

interface PhotoDestination {
    val route: String
}

object Home : PhotoDestination {
    override val route = "home"
}

private val gson = Gson()

object Details : PhotoDestination {
    override val route = "details"
    const val photoArg = "photo"
    val routeWithArgs = "${route}?photoArg={$photoArg}"
    val arguments = navArgument(photoArg) {//arguments for additional safety
        type = NavType.StringType
    }

    fun route(photo: Photo): String {
        return "$route?photoArg=${gson.toJson(photo)}"
    }
}