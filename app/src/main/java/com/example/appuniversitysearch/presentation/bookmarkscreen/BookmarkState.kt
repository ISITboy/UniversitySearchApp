package com.example.appuniversitysearch.presentation.bookmarkscreen

import com.example.domain.model.UniversityResponseItem

data class BookmarkState(
    val universities: List<UniversityResponseItem> = emptyList()
)
