package com.example.dan3.core.base

sealed class AppResult<out T> {
    data class Success<T>(val data: T) :AppResult<T>()
    data class Error(val mess :String) :AppResult<Nothing>()
}
