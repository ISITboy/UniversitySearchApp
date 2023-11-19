package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.UniversityResponseItem
import kotlinx.coroutines.flow.Flow

@Dao
interface UniversitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(universityResponseItem: UniversityResponseItem)

    @Delete
    suspend fun delete(universityResponseItem: UniversityResponseItem)

    @Query("SELECT * FROM universities")
    fun getUniversities(): Flow<List<UniversityResponseItem>>

    @Query("SELECT * FROM universities WHERE name=:name")
    suspend fun getUniversity(name:String): UniversityResponseItem
}