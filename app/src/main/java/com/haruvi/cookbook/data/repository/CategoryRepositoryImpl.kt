package com.haruvi.cookbook.data.repository

import com.haruvi.cookbook.data.remote.ApiService
import com.haruvi.cookbook.domain.entity.Categories
import com.haruvi.cookbook.domain.entity.CategoriesResponse
import com.haruvi.cookbook.utility.UiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CategoryRepositoryImpl(val apiService: ApiService) : CategoryRepository {
    override suspend fun getCategories(): Flow<UiData<List<Categories>>> = flow<UiData<List<Categories>>> {
        try {
            val response = apiService.getCategories()
            if(response.isSuccessful && response.body()!=null){
                emit(UiData.Success((response.body() as CategoriesResponse).categories))
            }else{
                emit(UiData.Error("Error: ${response.code()} - ${response.message()}"))
            }
        }catch (e:Exception){
            emit(UiData.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}