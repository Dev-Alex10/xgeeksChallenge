package com.example.xgeekschallenge.ui.details

import androidx.lifecycle.ViewModel
import com.example.xgeekschallenge.data.model.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailsViewModel : ViewModel() {
    private val _state = MutableStateFlow(ViewState.Loading)
    val state: StateFlow<ViewState> get() = _state //exposing state flow not mutable

    sealed interface ViewState {
        //dataclass auto generates equals,hashcode & copy
        data class Success(val photoList: List<Photo>) : ViewState
        data class Error(val t: Throwable) : ViewState
        object Loading : ViewState
    }
}