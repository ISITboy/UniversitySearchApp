package com.example.appuniversitysearch.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appuniversitysearch.ui.theme.AppUniversitySearchTheme
import com.example.domain.model.UniversityResponseItem

@Composable
fun UniversitiesCard(
    modifier: Modifier = Modifier,
    university: UniversityResponseItem,
    index: Int,
    onClick: (() -> Unit)? = null
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(5.dp)
            .clickable {
                onClick?.invoke()
            },
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(text = "${index + 1}.", modifier = modifier.padding(end = 5.dp), color = Color.White)
        Column() {
            Text(text = university.country, color = Color.White)
            Text(text = university.name, color = Color.White)
        }


    }


}

@Preview(showBackground = true)
@Composable
fun OnUniversitiesCardPreview() {
    AppUniversitySearchTheme {
        UniversitiesCard(
            university = UniversityResponseItem(
                alpha_two_code = "BY",
                country = "Belarus",
                domains = listOf("vsu.by"),
                name = "Vitebsk State University",
                state_province = null,
                web_pages = listOf("https://vsu.by/")
            ),
            index = 1
        )
    }
}