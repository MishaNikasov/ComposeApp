package com.example.composeapp.ui.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(

): ViewModel() {

    private val _list = MutableStateFlow<List<String>>(arrayListOf())
    val list = _list

    private val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState = _uiState

    private val _text = MutableStateFlow("")
    val text = _text

    fun loadItems() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading(true)
            delay(1000)
            val array = arrayListOf<String>()
            for (i in 1..10000) {
                array.add("Item $i")
            }
            _list.value = array
            _uiState.value = UiState.Loading(false)
        }
    }

    fun startRequest(requestText: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading(true)
            delay(1500)
            _text.value = "Text after response: $requestText"
            _uiState.value = UiState.Loading(false)
        }
    }

}