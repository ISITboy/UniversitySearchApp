package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.model.UniversityResponseItem
import com.example.domain.typeConverter.UniversitiesTypeConvertor

@Database(entities = [UniversityResponseItem::class], version = 4)
@TypeConverters(UniversitiesTypeConvertor::class)
abstract class UniversitiesDatabase : RoomDatabase(){
    abstract val universitiesDao:UniversitiesDao
}