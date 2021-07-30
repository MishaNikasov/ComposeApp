package com.example.composeapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.utils.DataState
import com.example.composeapp.utils.ErrorModel
import com.example.composeapp.utils.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel() {
    protected val mutableUiState = MutableLiveData<UiState>(UiState.Empty)
    val uiState: LiveData<UiState> = mutableUiState

    private val exceptionHandler = CoroutineExceptionHandler { _: CoroutineContext, throwable: Throwable ->
        mutableUiState.value = UiState.Loading(false)
        onException(ErrorModel.throwableError(throwable))
        Timber.d(throwable.localizedMessage)
    }

    fun <Model> handleRequest(request: (suspend () -> DataState<Model>), successAction: ((Model?) -> Unit)? = null, errorAction: ((ErrorModel) -> Unit)? = null, isLoading: Boolean = true) {
        viewModelScope.launch(exceptionHandler) {
            if (isLoading)
                mutableUiState.value = UiState.Loading(true)
            when (val state = request.invoke()) {
                is DataState.Success -> {
                    mutableUiState.value = UiState.Successes(state.data)
                    successAction?.invoke(state.data)
                }
                is DataState.Error -> {
                    mutableUiState.value = UiState.Error(state.errorModel)
                    errorAction?.invoke(state.errorModel)
                    Timber.e("${state.errorModel.messageText}, URL = ${state.errorModel.requestUrl}")
                }
            }
            if (isLoading)
                mutableUiState.value = UiState.Loading(false)
        }
    }

    open fun onException(errorModel: ErrorModel) {
        mutableUiState.value = UiState.Error(errorModel)
        Timber.e("${errorModel.messageText}, URL = ${errorModel.requestUrl}")
    }
}