package com.haruvi.cookbook.data.remote

import com.haruvi.cookbook.domain.entity.CategoriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("1/categories.php")
    suspend fun getCategories(): Response<CategoriesResponse>
}