package com.example.composeapp.utils

sealed class DataState<out R> {
    data class Success<out T>(val data: T): DataState<T>()
    data class Error(val errorModel: ErrorModel): DataState<Nothing>()
}