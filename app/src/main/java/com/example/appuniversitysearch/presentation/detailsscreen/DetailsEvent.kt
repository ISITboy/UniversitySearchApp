package com.example.appuniversitysearch.presentation.detailsscreen

import com.example.domain.model.UniversityResponseItem

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val university: UniversityResponseItem) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}