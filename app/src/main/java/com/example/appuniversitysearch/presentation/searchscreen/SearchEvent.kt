package com.example.appuniversitysearch.presentation.searchscreen

sealed class SearchEvent {

    data class UpdateSearchQueryName(val searchQueryName: String) : SearchEvent()

    data class UpdateSearchQueryCountry(val searchQueryCountry: String) : SearchEvent()

    object SearchUniversities : SearchEvent()
}