package com.haruvi.cookbook.data.repository

import com.haruvi.cookbook.domain.entity.Categories
import com.haruvi.cookbook.domain.entity.CategoriesResponse
import com.haruvi.cookbook.utility.UiData
import kotlinx.coroutines.flow.Flow


interface CategoryRepository {
    suspend fun getCategories(): Flow<UiData<List<Categories>>>
}