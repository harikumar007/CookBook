package com.haruvi.cookbook.utility

sealed class UiData<out T> {
    object Loading: UiData<Nothing>()
    data class Success<out T>(val response: T): UiData<T>()
    data class Error(val error: String): UiData<Nothing>()
}