package com.example.xgeekschallenge.ui.details

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xgeekschallenge.R
import com.example.xgeekschallenge.data.model.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.Instant
import java.time.format.DateTimeFormatter

class DetailsViewModel(photo: Photo) : ViewModel() {
    private val _state = MutableStateFlow(ViewState(photo, true))
    val state: StateFlow<ViewState> get() = _state //exposing state flow not mutable

    data class ViewState(
        val photo: Photo,
        val visible: Boolean
    ) {
        val metadata: String  //only called in a compose fun
            @Composable get() = stringResource(
                id = R.string.photo_detail_date_taken,
                photo.dateTaken
            ) + stringResource(
                id = R.string.photo_detail_date_uploaded,
                dateFormatter(photo.dateUploaded)
            ) +
                    stringResource(
                        id = R.string.photo_url,
                        photo.url
                    ) + stringResource(
                id = R.string.photo_detail_owner,
                photo.owner
            ) + stringResource(
                id = R.string.photo_detail_size, if (photo.width.isNullOrBlank()) {
                    stringResource(id = R.string.width_unavailable)
                } else {
                    photo.width
                }, if (photo.height.isNullOrBlank()) {
                    stringResource(id = R.string.height_unavailable)
                } else {
                    photo.height
                }
            )

    }

    fun changeVisibleState() {
        _state.update { it.copy(visible = !state.value.visible) }
    }
}

@SuppressLint("NewApi")
fun dateFormatter(date: String): String {
    val dateUploaded = DateTimeFormatter.ISO_INSTANT.format(
        Instant.ofEpochSecond(
            date.substringAfter("[").toLong()
        )
    ).substringBefore("T")
    val timeUploaded = DateTimeFormatter.ISO_INSTANT.format(
        Instant.ofEpochSecond(
            date.substringAfter("[").toLong()
        )
    ).substringBefore("Z").substringAfter("T")
    return dateUploaded + timeUploaded
}

class MyViewModelFactory(private val photo: Photo) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = DetailsViewModel(photo) as T
}

