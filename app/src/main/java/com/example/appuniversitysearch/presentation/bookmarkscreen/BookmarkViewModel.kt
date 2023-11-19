package com.example.appuniversitysearch.presentation.bookmarkscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usescase.databaseUsesCases.GetSavedUniversities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getSavedUniversities: GetSavedUniversities
) : ViewModel(){

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    init {
        getUniversities()
    }

    private fun getUniversities() {
        getSavedUniversities().onEach {
            _state.value = _state.value.copy(universities = it)
        }.launchIn(viewModelScope)
    }
}