package com.example.xgeekschallenge.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailsScreen(
    detailsViewModel: DetailsViewModel = viewModel(),
    photoUrl: String?
) {
    val state =
        detailsViewModel.state.collectAsState().value //"by" to use state directly as ViewState
    Column {
        Text(text = "Details Screen")
    }
//    PhotoCard(photo = state.photo)
}

@Preview
@Composable
private fun HomeViewPreview() {
    MaterialTheme {
        Surface {
            DetailsScreen(photoUrl = "")
        }
    }
}