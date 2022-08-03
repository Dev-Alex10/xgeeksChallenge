package com.example.xgeekschallenge.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(photo.url)
//                .crossfade(true)
//                .build(),
//            contentDescription = null, // decorative
//            modifier = imageModifier,
//            contentScale = ContentScale.Crop,
//            placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
//        )
        SubcomposeAsyncImage(
            model = photo.url,
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop,
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }
    }
}

@Preview
@Composable
fun PhotoCardPreview() {
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