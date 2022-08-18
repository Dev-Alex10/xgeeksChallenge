package com.example.xgeekschallenge

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.xgeekschallenge.ui.theme.XGeeksChallengeTheme


@Composable
fun PhotoApp() {
    XGeeksChallengeTheme {
        val navController = rememberNavController()
        Scaffold {
            PhotoNavHost(navController, Modifier.padding(it))
        }
    }
}


