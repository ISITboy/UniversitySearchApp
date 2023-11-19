package com.example.appuniversitysearch.presentation.detailsscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appuniversitysearch.presentation.utils.UIComponent
import com.example.domain.model.UniversityResponseItem
import com.example.domain.usescase.databaseUsesCases.DeleteSavedUniversity
import com.example.domain.usescase.databaseUsesCases.GetSavedUniversity
import com.example.domain.usescase.databaseUsesCases.InsertUniversity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getSavedUniversity:GetSavedUniversity,
    private val deleteSavedUniversity: DeleteSavedUniversity,
    private val insertUniversity: InsertUniversity
) :ViewModel(){
    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val uni = getSavedUniversity(name = event.university.name)
                    if (uni == null){
                        insertUni(uni = event.university)
                    }else{
                        deleteUni(uni = event.university)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }


    private suspend fun deleteUni(uni: UniversityResponseItem) {
        deleteSavedUniversity(universityResponseItem = uni)
        sideEffect = UIComponent.Toast("University deleted")
    }

    private suspend fun insertUni(uni: UniversityResponseItem) {
        insertUniversity(universitiesResponseItem = uni)
        sideEffect = UIComponent.Toast("University Inserted")
    }
}