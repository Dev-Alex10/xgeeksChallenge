package com.example.xgeekschallenge.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeView(homeViewModel: HomeViewModel = viewModel()) {
    val state = homeViewModel.state.collectAsState().value //"by" to use state directly as ViewState
    Column {
        TextField(value = state.text, onValueChange = { homeViewModel.onTextChanged(it) })
        when (state.photoState) {
            is HomeViewModel.PhotosLoadingState.Error -> Text(state.photoState.t.toString())
            HomeViewModel.PhotosLoadingState.Loading -> Text(text = "Loading")
            is HomeViewModel.PhotosLoadingState.Success -> ListPhotos(photos = state.photoState.photoList)
        }
    }
}

@Preview
@Composable
private fun HomeViewPreview() {
    MaterialTheme {
        Surface {
            HomeView()
        }
    }
}