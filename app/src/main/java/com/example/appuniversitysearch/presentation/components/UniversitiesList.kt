package com.example.appuniversitysearch.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.domain.model.UniversityResponseItem

@Composable
fun UniversitiesList(
    modifier: Modifier = Modifier,
    universities: LazyPagingItems<UniversityResponseItem>,
    onClick: (UniversityResponseItem) -> Unit
) {

    val bottomPadding = PaddingValues().calculateBottomPadding()
    if (handlePagingResult(universities = universities)) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(universities.itemSnapshotList) { index, _ ->
                universities[index]?.let { university ->
                    UniversitiesCard(university = university, index = index, onClick = {onClick(university)})
                    Spacer(modifier = modifier.height(10.dp))
                    Log.d("MyLog1","university${university}")
                }
            }
        }
    }
}

@Composable
fun UniversitiesList(
    modifier: Modifier = Modifier,
    universities: List<UniversityResponseItem>,
    onClick: (UniversityResponseItem) -> Unit
) {
    val bottomPadding = PaddingValues().calculateBottomPadding()

    if (universities.isEmpty()) {
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = bottomPadding),
        contentPadding = PaddingValues(10.dp)
    ) {
        itemsIndexed(universities) { index, _ ->
            universities[index]?.let { university ->
                UniversitiesCard(university = university, index = index,onClick = {onClick(university)})
                Spacer(modifier = modifier.height(10.dp))
            }
        }
    }
}


