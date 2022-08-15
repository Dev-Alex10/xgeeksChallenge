package com.example.xgeekschallenge.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.xgeekschallenge.R
import com.example.xgeekschallenge.data.model.Photo

@Composable
fun PhotoCard(
    photo: Photo,
    modifier: Modifier = Modifier
) {
    val imageModifier = modifier
        .heightIn(min = 300.dp, max = 400.dp)
        .widthIn(max = 500.dp)
        .fillMaxWidth()
        .fillMaxHeight()
        .clip(shape = MaterialTheme.shapes.medium)
//        .clickable {
//            Toast
//                .makeText(
//                    LocalContext.current,
//                    DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochSecond(photo.metaData[0].toLong())),
//                    Toast.LENGTH_LONG
//                )
//                .show()
//        }
    Box(modifier = Modifier.border(3.dp, Color.Black)) {
        val context = LocalContext.current
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(photo.url)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.FillBounds
        ) {
            when (val state = painter.state) {
                AsyncImagePainter.State.Empty -> Text(text = "Empty")
                is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
                is AsyncImagePainter.State.Error ->
                    Image(
                        painter = painterResource(id = R.drawable.error),
                        contentDescription = null,
                        Modifier.clickable {
                            Toast
                                .makeText(
                                    context,
                                    "We're sorry an error occurred -> ${state.result.throwable.message}",
                                    Toast.LENGTH_LONG
                                )
                                .show()
                        }
                    )
            }
        }
    }
}

@Composable
fun ListPhotos(
    photos: List<Photo>,
    text: String = "Search result",
    onImageClick: (Photo) -> Unit
) {
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
                    photo = photo,
                    modifier = Modifier.clickable {
                        onImageClick(photo)
                    }
                )
            }
        }
    }
}

//@Preview
//@Composable
//private fun PhotoCardPreview() {
//    MaterialTheme {
//        Surface {
//            PhotoCard(
//                Photo(
//                    "1",
//                    "https://live.staticflickr.com/7372/12502775644_acfd415fa7_w.jpg",
//                    "List",
//                    ArrayList()
//                )
//            )
//        }
//    }
//}

//@Preview
//@Composable
//private fun ListPhotoCardPreview() {
//    val photos = listOf(
//        Photo(
//            "1", "https://live.staticflickr.com/7372/12502775644_acfd415fa7_w.jpg", "xD",
//            ArrayList()
//        ),
//        Photo(
//            "2", "https://live.staticflickr.com/65535/52261500552_70231c5eb6.jpg", "Cat","",""
//            ArrayList()
//        )
//    )
//    MaterialTheme {
//        Surface {
//            ListPhotos(photos = photos, onImageClick = {})
//        }
//    }
//}