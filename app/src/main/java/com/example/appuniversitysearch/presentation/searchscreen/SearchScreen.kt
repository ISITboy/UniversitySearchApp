package com.example.appuniversitysearch.presentation.searchscreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.appuniversitysearch.presentation.utils.Dimens.MediumPadding1
import com.example.appuniversitysearch.presentation.components.UniversitiesList
import com.example.appuniversitysearch.presentation.searchscreen.components.FormsSearchBar
import com.example.domain.model.UniversityResponseItem

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navigateToDetails:(UniversityResponseItem) -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        FormsSearchBar(state = viewModel.state.value, event = viewModel::onEvent)
        viewModel.state.value.universities?.let {

            UniversitiesList(universities = it.collectAsLazyPagingItems(), onClick = navigateToDetails)

        }

    }
}