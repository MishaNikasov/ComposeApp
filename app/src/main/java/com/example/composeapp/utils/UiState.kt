package com.example.composeapp.utils

sealed class UiState {
    data class Error(val errorModel: ErrorModel): UiState()
    data class Loading(val inProgress: Boolean): UiState()
    data class Successes<Data>(val data: Data): UiState()
    object Empty: UiState()
}