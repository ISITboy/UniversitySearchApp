package com.example.domain.usescase

import androidx.paging.PagingData
import com.example.domain.model.UniversityResponseItem
import com.example.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUniversities @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    operator fun invoke(
        searchedName: String,
        searchedCountry: String
    ): Flow<PagingData<UniversityResponseItem>>{
        return universityRepository.getUniversities(searchedName,searchedCountry)
    }
}