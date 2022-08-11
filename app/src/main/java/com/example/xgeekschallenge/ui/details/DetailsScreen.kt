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
import androidx.compose.ui.text.style.TextAlign
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
    var listPhotoMetadata = photoMetadata.split(",")

    val dateUploaded = "Date Uploaded: " + DateTimeFormatter.ISO_INSTANT.format(
        Instant.ofEpochSecond(
            listPhotoMetadata[0].substringAfter("[").toLong()
        )
    )
    val dateTaken = "Date Taken: " + listPhotoMetadata[1]
    var owner = listPhotoMetadata[2]
    var width = listPhotoMetadata[3]
    var height = listPhotoMetadata[4].substringBefore("]")
    owner = checkNull(varCheck = owner, text = "Owner")
    width = checkNull(varCheck = width, text = "Width")
    height = checkNull(varCheck = height, text = "Height")
    owner = "Owner: $owner"
    width = "Owner: $width"
    height = "Owner: $height"
    listPhotoMetadata = listOf(dateUploaded, dateTaken, owner, width, height)

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

//        Row(horizontalArrangement = Arrangement.Center) {
        AnimatedVisibility(visible = !visible) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
            ) {
                items(listPhotoMetadata) { photoMetadata ->
                    Text(
                        text = photoMetadata,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .clickable {
                                visible = !visible
                            })
                }
            }
//        }
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