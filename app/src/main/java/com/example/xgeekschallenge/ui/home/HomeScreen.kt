package com.example.xgeekschallenge.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xgeekschallenge.data.model.Photo

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(), onImageClick: (Photo) -> Unit) {
    val state = homeViewModel.state.collectAsState().value //"by" to use state directly as ViewState
    val focusManager = LocalFocusManager.current
    Column {
        OutlinedTextField(
            value = state.text,
            onValueChange = { homeViewModel.onTextChanged(it) },
            label = {
                Text(
                    text = "Search Bar", color = MaterialTheme.colors.primary
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            })
        )

        when (state.photoState) {
            is HomeViewModel.PhotosLoadingState.Error -> Text(state.photoState.t.toString())
            HomeViewModel.PhotosLoadingState.Loading -> Text(text = "Loading")
            is HomeViewModel.PhotosLoadingState.Success ->
                ListPhotos(
                    photos = state.photoState.photoList,
                    text = if (state.text.any()) {
                        state.text
                    } else
                        "Recent",
                    onImageClick = onImageClick
                )
        }
    }

}

@Preview
@Composable
private fun HomeViewPreview() {
    MaterialTheme {
        Surface {
            HomeScreen(onImageClick = {})
        }
    }
}