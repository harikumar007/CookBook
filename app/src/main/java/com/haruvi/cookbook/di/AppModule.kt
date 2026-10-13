package com.haruvi.cookbook.di

import com.haruvi.cookbook.data.remote.ApiService
import com.haruvi.cookbook.data.repository.CategoryRepository
import com.haruvi.cookbook.data.repository.CategoryRepositoryImpl
import com.haruvi.cookbook.domain.usecase.GetCategoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideCategoryRepository(apiService: ApiService): CategoryRepository{
        return CategoryRepositoryImpl(apiService)
    }


    @Provides
    @Singleton
    fun provideGetCategoryUseCase(categoryRepository: CategoryRepository): GetCategoryUseCase{
        return GetCategoryUseCase(categoryRepository)
    }

}