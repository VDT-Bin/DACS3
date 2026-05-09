package com.example.dan3.core.base

sealed class UiEvent {
    data class ShowMess(val mess: String) : UiEvent()
    data class ShowError(val mess: String) : UiEvent()
    object NavigateBack : UiEvent()
    object NavigateHome : UiEvent()
}