package com.example.xgeekschallenge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.xgeekschallenge.data.model.Photo
import com.example.xgeekschallenge.ui.details.DetailsScreen
import com.example.xgeekschallenge.ui.details.MyViewModelFactory
import com.example.xgeekschallenge.ui.home.HomeScreen
import com.google.gson.Gson

@Composable
fun PhotoNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = Home.route, modifier = modifier) {
        composable(route = Home.route) {
            HomeScreen(onImageClick = { photo ->
                navController.navigateToSinglePhoto(
                    photo = photo
                )
            })
        }
        composable(
            route = Details.routeWithArgs,
            arguments = listOf(Details.arguments)
        ) {
            val photo =
                Gson().fromJson(it.arguments?.getString(Details.photoArg), Photo::class.java)
            DetailsScreen(detailsViewModel = viewModel(factory = MyViewModelFactory(photo)))
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

private fun NavHostController.navigateToSinglePhoto(photo: Photo) {
    this.navigateSingleTopTo(Details.route(photo))
}
