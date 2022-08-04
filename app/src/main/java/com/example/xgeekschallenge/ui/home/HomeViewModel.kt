package com.example.xgeekschallenge.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xgeekschallenge.data.WebClient
import com.example.xgeekschallenge.data.model.Photo
import com.example.xgeekschallenge.data.model.PhotoResponse
import com.example.xgeekschallenge.data.model.toDomain
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(ViewState())
    val state: StateFlow<ViewState> get() = _state //exposing state flow not mutable
    private var photoJob: Job? = null

    init {
        getPhotoList(searchTerm = "cat")
    }

    sealed interface PhotosLoadingState {
        //dataclass auto generates equals,hashcode & copy
        data class Success(val photoList: List<Photo>) : PhotosLoadingState
        data class Error(val t: Throwable) : PhotosLoadingState
        object Loading : PhotosLoadingState
    }

    data class ViewState(
        val text: String = "",
        val photoState: PhotosLoadingState = PhotosLoadingState.Loading
    )

    fun viewState(): Flow<ViewState> {
        return state
    }

    fun onTextChanged(text: String) {
        _state.update { it.copy(text = text) }
        getPhotoList(text)
    }

    private fun getPhotoList(searchTerm: String) {
        photoJob?.cancel()

        _state.update { it.copy(photoState = PhotosLoadingState.Loading) }
        photoJob = viewModelScope.launch {
            delay(500) //not consuming to much of the API
            try {
                val photos = requestPhotosFromAPI(searchTerm)
                _state.update { it.copy(photoState = PhotosLoadingState.Success(photos)) }
            } catch (t: Throwable) {
                Log.e("VIEWMODEL", "${t.message}")
                //sealed class type exceptions
                _state.update { it.copy(photoState = PhotosLoadingState.Error(t)) }
            }
        }
    }

    private suspend fun requestPhotosFromAPI(searchTerm: String): List<Photo> {
        if (searchTerm.isBlank()) {
            return emptyList()
        }
        val searchResponse = WebClient.client.fetchImages(searchTerm)
        return searchResponse.requestMetaData.photos.map(PhotoResponse::toDomain)//.onEach(::println)
    }
}

