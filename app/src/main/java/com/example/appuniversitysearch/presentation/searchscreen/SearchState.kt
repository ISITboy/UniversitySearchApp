package com.example.appuniversitysearch.presentation.searchscreen

import androidx.paging.PagingData
import com.example.domain.model.UniversityResponseItem
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQueryName: String = "",
    val searchQueryCountry: String = "",
    val universities: Flow<PagingData<UniversityResponseItem>>? = null
)