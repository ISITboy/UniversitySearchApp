package com.example.appuniversitysearch.presentation.mainscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.appuniversitysearch.presentation.searchscreen.SearchEvent
import com.example.appuniversitysearch.presentation.searchscreen.SearchState
import com.example.domain.usescase.GetUniversities
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUniversities: GetUniversities
):ViewModel(){

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQueryName -> {
                _state.value = _state.value.copy(searchQueryName = event.searchQueryName)
            }
            is SearchEvent.UpdateSearchQueryCountry -> {
                _state.value = _state.value.copy(searchQueryCountry = event.searchQueryCountry)
            }
            is SearchEvent.SearchUniversities -> {
                searchSearchUniversities()
            }
        }
    }

    private fun searchSearchUniversities() {
        val universities = getUniversities(
            searchedName = _state.value.searchQueryName,
            searchedCountry = _state.value.searchQueryCountry
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(universities = universities)
    }
}