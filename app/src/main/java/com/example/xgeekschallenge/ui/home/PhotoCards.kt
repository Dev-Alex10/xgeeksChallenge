package com.example.xgeekschallenge.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.xgeekschallenge.data.model.Photo

@Composable
fun PhotoCard(photo: Photo, modifier: Modifier = Modifier) { //add photo : Photo
    val imageModifier = Modifier
        .heightIn(min = 300.dp, max = 360.dp)
        .widthIn(max = 400.dp)
        .fillMaxWidth()
        .clip(shape = MaterialTheme.shapes.medium)
        .clickable { }

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(photo.url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = imageModifier,
        contentScale = ContentScale.Crop
    ) {
        when (painter.state) {
            AsyncImagePainter.State.Empty -> Text(text = "Empty")
            is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            is AsyncImagePainter.State.Error ->
                Image(
                    painter = ColorPainter(Color.Black),
                    contentDescription = null
                )
        }
    }
}

@Composable
fun ListPhotos(photos: List<Photo>, text: String = "Search result") {
    Column {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = text,
            fontSize = 24.sp,
            style = MaterialTheme.typography.subtitle1
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
        ) {
            items(photos) { photo ->
                PhotoCard(
                    photo
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