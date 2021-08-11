package com.example.composeapp.ui.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(

): ViewModel() {

    private val _text = MutableStateFlow("")
    val text = _text

    fun startRequest(requestText: String) {
        viewModelScope.launch {
            delay(1500)
            _text.value = "Text after response: $requestText"
        }
    }

}