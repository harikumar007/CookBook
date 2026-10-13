package com.haruvi.cookbook.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haruvi.cookbook.domain.entity.Categories
import com.haruvi.cookbook.domain.entity.CategoriesResponse
import com.haruvi.cookbook.domain.usecase.GetCategoryUseCase
import com.haruvi.cookbook.utility.UiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val getCategoryUseCase: GetCategoryUseCase): ViewModel() {

    private var _uiState = MutableStateFlow<UiData<List<Categories>>>(UiData.Loading)
    val uiState: StateFlow<UiData<List<Categories>>>
        get() = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            getCategoryUseCase().collect { state ->
                _uiState.value = state
            }
        }
    }

}