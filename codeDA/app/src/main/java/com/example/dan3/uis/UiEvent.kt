package com.example.dan3.uis

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
    data class NavigateTo(val route: String): UiEvent()
    data object Refresh : UiEvent()
}