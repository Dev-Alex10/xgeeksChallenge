package com.example.xgeekschallenge

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface PhotoDestination {
    val route: String
}

object Home : PhotoDestination {
    override val route = "home"
}

object Details : PhotoDestination {
    override val route = "details"
    const val photoUrlArg = "photo_url"
    const val photoMetadataArg = "photo_metadata"
    val routeWithArgs = "${route}/{$photoUrlArg}/{$photoMetadataArg}"
    val arguments = listOf(navArgument(photoUrlArg) {//arguments for additional safety
        type = NavType.StringType
    }, navArgument(photoMetadataArg) {
        type = NavType.StringType
    })
}

val photoScreens = listOf(Home, Details)