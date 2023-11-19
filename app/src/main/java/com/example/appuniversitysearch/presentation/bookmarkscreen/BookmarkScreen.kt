package com.example.appuniversitysearch.presentation.bookmarkscreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.appuniversitysearch.R
import com.example.appuniversitysearch.presentation.utils.Dimens.MediumPadding1
import com.example.appuniversitysearch.presentation.components.UniversitiesList
import com.example.domain.model.UniversityResponseItem

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails:(UniversityResponseItem) -> Unit
) {
    Log.d("MyLog","BookmarkScreen")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                id = R.color.text_title
            )
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        UniversitiesList(
            universities =state.universities, onClick = navigateToDetails
        )
    }
}