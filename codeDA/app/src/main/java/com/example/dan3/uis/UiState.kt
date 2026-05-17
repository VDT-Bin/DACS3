package com.example.dan3.uis

sealed interface UiState<out T> {
    data class Success <T>(val data : T) : UiState<T>
    data class Error(val mess :String) : UiState<Nothing>
    data object Loading : UiState<Nothing>
}