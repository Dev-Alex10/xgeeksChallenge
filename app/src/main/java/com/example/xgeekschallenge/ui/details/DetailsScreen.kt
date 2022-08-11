package com.example.xgeekschallenge.ui.details

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.xgeekschallenge.R
import java.time.Instant
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun DetailsScreen(
    photoUrl: String = "",
    photoMetadata: String = ""
) {
    var visible by remember {
        mutableStateOf(true)
    }
    val imageModifier = Modifier
        .heightIn(min = 400.dp, max = 500.dp)
        .widthIn(max = 500.dp)
        .fillMaxWidth()
        .fillMaxHeight()
        .clip(shape = MaterialTheme.shapes.large)
    val listPhotoMetadata = metadataFormatter("$photoMetadata, $photoUrl")

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = !visible) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
            ) {
                items(listPhotoMetadata) { photoMetadata ->
                    Text(
                        text = photoMetadata,
                        modifier = Modifier
                            .clickable {
                                visible = !visible
                            })
                }
            }
        }
        AnimatedVisibility(visible = visible) {
            val context = LocalContext.current
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(context)
                    .data(photoUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.FillBounds
            ) {
                when (val state = painter.state) {
                    AsyncImagePainter.State.Empty -> Text(text = "Empty")
                    is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                    is AsyncImagePainter.State.Success ->
                        SubcomposeAsyncImageContent(Modifier.clickable {
                            visible = !visible
                        })

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
}

@Composable
private fun metadataFormatter(photoMetadata: String): List<String> {
    var listPhotoMetadata = photoMetadata.split(",")
    val extendedDateUploaded = dateFormatter(listPhotoMetadata)
    val dateTaken = "Date Taken: ${listPhotoMetadata[1]}"
    val owner = "Owner Name: ${listPhotoMetadata[2]}"
    var width = listPhotoMetadata[3]
    var height = listPhotoMetadata[4].substringBefore("]")
    val url = "Url: ${listPhotoMetadata[5]}"
    width = checkNull(varCheck = width, text = "Width")
    height = checkNull(varCheck = height, text = "Height")
    width = "Width: $width"
    height = "Height: $height"
    listPhotoMetadata = listOf(extendedDateUploaded, dateTaken, owner, width, height, url)

    return listPhotoMetadata
}

@SuppressLint("NewApi")
@Composable
private fun dateFormatter(listPhotoMetadata: List<String>): String {
    val dateUploaded = DateTimeFormatter.ISO_INSTANT.format(
        Instant.ofEpochSecond(
            listPhotoMetadata[0].substringAfter("[").toLong()
        )
    ).substringBefore("T")
    val timeUploaded = DateTimeFormatter.ISO_INSTANT.format(
        Instant.ofEpochSecond(
            listPhotoMetadata[0].substringAfter("[").toLong()
        )
    ).substringBefore("Z").substringAfter("T")
    return "Date Uploaded: $dateUploaded $timeUploaded"
}

@Composable
private fun checkNull(varCheck: String, text: String): String {
    var returnText = varCheck
    if (returnText == " null") {
        returnText = "$text unavailable"
    }
    return returnText
}

@Preview
@Composable
private fun HomeViewPreview() {
    MaterialTheme {
        Surface {
            DetailsScreen()
        }
    }
}