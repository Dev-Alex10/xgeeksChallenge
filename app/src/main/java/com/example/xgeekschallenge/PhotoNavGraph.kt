package com.example.xgeekschallenge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.xgeekschallenge.ui.details.DetailsScreen
import com.example.xgeekschallenge.ui.home.HomeScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun PhotoNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = Home.route, modifier = modifier) {
        composable(route = Home.route) {
            HomeScreen(onImageClick = { photoUrl ->
                navController.navigateToSinglePhoto(
                    URLEncoder.encode(
                        photoUrl,
                        StandardCharsets.UTF_8.toString()
                    ) //to recognize the url it needs to be encoded
                )
            })
        }
        composable(
            route = Details.routeWithArgs,
            arguments = Details.arguments
        ) {
            val photoUrl = it.arguments?.getString(Details.photoUrlArg)
            DetailsScreen(photoUrl = photoUrl)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun NavHostController.navigateToSinglePhoto(photoUrl: String) {
    this.navigateSingleTopTo("${Details.route}/$photoUrl")
}