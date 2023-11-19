package com.example.domain.usescase.databaseUsesCases

import com.example.domain.model.UniversityResponseItem
import com.example.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedUniversity @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    suspend operator fun invoke(name:String): UniversityResponseItem {
        return universityRepository.getSavedUniversity(name)
    }
}