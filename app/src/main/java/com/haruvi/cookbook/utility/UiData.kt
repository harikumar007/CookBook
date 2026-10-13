package com.haruvi.cookbook.utility

import com.haruvi.cookbook.domain.entity.CategoriesResponse

sealed class UiData<out T> {
    object Loading: UiData<Nothing>()
    data class Success<out T>(val response: T): UiData<T>()
    data class Error(val error: String): UiData<Nothing>()
}