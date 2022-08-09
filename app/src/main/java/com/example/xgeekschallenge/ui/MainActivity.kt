package com.example.xgeekschallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.xgeekschallenge.PhotoApp
import com.example.xgeekschallenge.ui.home.HomeScreen
import com.example.xgeekschallenge.ui.theme.XGeeksChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XGeeksChallengeTheme {
                // A surface container using the 'background' color from the theme
                PhotoApp()
            }
        }
    }
}