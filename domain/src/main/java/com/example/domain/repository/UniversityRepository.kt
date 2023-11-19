package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.UniversityResponseItem
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    fun getUniversities(searchedName: String, searchedCountry: String):
            Flow<PagingData<UniversityResponseItem>>

    fun getSavedUniversities():Flow<List<UniversityResponseItem>>

    suspend fun getSavedUniversity(name:String):UniversityResponseItem

    suspend fun insertUniversity(universityResponseItem : UniversityResponseItem)

    suspend fun deleteSavedUniversity(universityResponseItem: UniversityResponseItem)


}