package com.example.appuniversitysearch.di

import android.app.Application
import androidx.room.Room
import com.example.data.local.UniversitiesDao
import com.example.data.local.UniversitiesDatabase
import com.example.data.remote.Constants.BASE_URL
import com.example.data.remote.UniversityApi
import com.example.data.repository.UniversityRepositoryImpl
import com.example.domain.repository.UniversityRepository
import com.example.domain.typeConverter.UniversitiesTypeConvertor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiInstance(): UniversityApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UniversityApi::class.java)
    }

    @Provides
    @Singleton
    fun providesUniversityRepository(
        universityApi: UniversityApi,
        universitiesDao: UniversitiesDao
    ):UniversityRepository = UniversityRepositoryImpl(
        universityApi = universityApi, universitiesDao = universitiesDao
    )

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): UniversitiesDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = UniversitiesDatabase::class.java,
            name = "uni_db"
        ).fallbackToDestructiveMigration()
            .addTypeConverter(UniversitiesTypeConvertor())
            .build()
    }

    @Provides
    @Singleton
    fun provideUniversitiesDao(
        universitiesDatabase: UniversitiesDatabase
    ):UniversitiesDao =universitiesDatabase.universitiesDao
}