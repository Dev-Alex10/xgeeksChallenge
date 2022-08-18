package com.example.xgeekschallenge.ui.details

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

@Composable
fun DetailsScreen(
    detailsViewModel: DetailsViewModel
) {

    val state = detailsViewModel.state.collectAsState().value
    val imageModifier = Modifier
        .heightIn(min = 100.dp, max = 500.dp)
        .widthIn(max = 500.dp)
        .fillMaxWidth()
        .clip(shape = MaterialTheme.shapes.large)
//    val listPhotoMetadata = metadataFormatter("$photoMetadata, $photoUrl")


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.border(3.dp, Color.Black)) {
            val context = LocalContext.current
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(context)
                    .data(state.photo.url)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.FillBounds,

                ) {
                when (val state = painter.state) {
                    AsyncImagePainter.State.Empty -> Text(text = "Empty")
                    is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                    is AsyncImagePainter.State.Success ->
                        SubcomposeAsyncImageContent(Modifier.clickable {
                            detailsViewModel.changeVisibleState()
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
        AnimatedVisibility(visible = !state.visible) {
            SelectionContainer {
                Text(
                    text = state.metadata
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeViewPreview() {
    MaterialTheme {
        Surface {
//            DetailsScreen()
        }
    }
}