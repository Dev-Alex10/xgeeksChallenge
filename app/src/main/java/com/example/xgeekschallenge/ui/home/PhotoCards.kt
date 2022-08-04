package com.example.xgeekschallenge.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.xgeekschallenge.data.model.Photo

@Composable
fun PhotoCard(photo: Photo, modifier: Modifier = Modifier) { //add photo : Photo
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val imageModifier = Modifier
            .heightIn(min = 180.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
        SubcomposeAsyncImage(
            model = photo.url,
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop,
        ) {
            val state = painter.state
            when (state) {
                AsyncImagePainter.State.Empty -> Text(text = "Empty")
                is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
                is AsyncImagePainter.State.Error -> Text(text = state.result.throwable.stackTraceToString())
            }
        }
    }
}

@Composable
fun ListPhotos(photos: List<Photo>, modifier: Modifier = Modifier) {
    Column {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Cats",
            style = MaterialTheme.typography.subtitle1
        )
        LazyRow(modifier = Modifier.padding(end = 16.dp)) {
            items(photos) { photo ->
                PhotoCard(
                    photo,
                    Modifier.padding(start = 16.dp, bottom = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PhotoCardPreview() {
    MaterialTheme {
        Surface {
            PhotoCard(
                Photo(
                    "1",
                    "https://live.staticflickr.com/7372/12502775644_acfd415fa7_w.jpg",
                    "List"
                )
            )
        }
    }
}

@Preview
@Composable
private fun ListPhotoCardPreview() {
    val photos = listOf(
        Photo("1", "https://live.staticflickr.com/7372/12502775644_acfd415fa7_w.jpg", "xD"),
        Photo("2", "https://live.staticflickr.com/65535/52261500552_70231c5eb6.jpg", "Cat")
    )
    MaterialTheme {
        Surface {
            ListPhotos(photos = photos)
        }
    }
}