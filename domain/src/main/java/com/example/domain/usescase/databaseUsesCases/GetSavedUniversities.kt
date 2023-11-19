package com.example.domain.usescase.databaseUsesCases

import com.example.domain.model.UniversityResponseItem
import com.example.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedUniversities @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    operator fun invoke(): Flow<List<UniversityResponseItem>> {
        return universityRepository.getSavedUniversities()
    }
}