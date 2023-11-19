package com.example.domain.usescase.databaseUsesCases

import com.example.domain.model.UniversityResponseItem
import com.example.domain.repository.UniversityRepository
import javax.inject.Inject

class InsertUniversity @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    suspend operator fun invoke(universitiesResponseItem:UniversityResponseItem) {
        return universityRepository.insertUniversity(universitiesResponseItem)
    }
}