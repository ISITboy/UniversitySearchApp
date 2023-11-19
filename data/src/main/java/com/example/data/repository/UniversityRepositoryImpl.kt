package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.local.UniversitiesDao
import com.example.data.remote.Constants.DEFAULT_PAGE_SIZE
import com.example.data.remote.UniversityApi
import com.example.data.remote.paging.UniversityPagingSource
import com.example.domain.model.UniversityResponseItem
import com.example.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val universityApi: UniversityApi,
    private val universitiesDao: UniversitiesDao
):UniversityRepository {

    override fun getUniversities(
        searchedName: String,
        searchedCountry: String
    ): Flow<PagingData<UniversityResponseItem>> {
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, prefetchDistance = 5),
            pagingSourceFactory = {
                UniversityPagingSource(
                    universityApi = universityApi,searchedName,searchedCountry)
            }
        ).flow
    }

    override fun getSavedUniversities(): Flow<List<UniversityResponseItem>> {
        return universitiesDao.getUniversities()
    }

    override suspend fun getSavedUniversity(name:String): UniversityResponseItem {
        return universitiesDao.getUniversity(name)
    }

    override suspend fun insertUniversity(universityResponseItem: UniversityResponseItem) {
        return universitiesDao.insert(universityResponseItem)
    }

    override suspend fun deleteSavedUniversity(universityResponseItem: UniversityResponseItem) {
        return universitiesDao.delete(universityResponseItem)
    }


}