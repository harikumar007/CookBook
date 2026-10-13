package com.haruvi.cookbook.domain.usecase

import com.haruvi.cookbook.data.repository.CategoryRepository
import com.haruvi.cookbook.domain.entity.Categories
import com.haruvi.cookbook.domain.entity.CategoriesResponse
import com.haruvi.cookbook.utility.UiData
import kotlinx.coroutines.flow.Flow

class GetCategoryUseCase(val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): Flow<UiData<List<Categories>>> {
        return categoryRepository.getCategories()
    }
}