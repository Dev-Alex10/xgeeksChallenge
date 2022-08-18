package com.example.xgeekschallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import com.example.xgeekschallenge.PhotoApp
import com.example.xgeekschallenge.ui.theme.XGeeksChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XGeeksChallengeTheme {
                Scaffold {
                    PhotoApp()
                }
            }
        }
    }
}