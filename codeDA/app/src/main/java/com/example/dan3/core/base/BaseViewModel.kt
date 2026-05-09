package com.example.dan3.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val _event = MutableSharedFlow<UiEvent>()
    val event : SharedFlow<UiEvent> = _event.asSharedFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> = _isLoading.asStateFlow()

    protected suspend fun sendEvent(event : UiEvent){
        _event.emit(event)
    }
    protected suspend fun setLoading(loading : Boolean){
        _isLoading.value = loading
    }

    protected fun lauchWithLoading(block : suspend () -> Unit){
        viewModelScope.launch {
            setLoading(true)
            try {
                block()
            }finally{
               setLoading(false)
            }
        }
    }
}