package com.example.composeapp.ui.test

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeapp.utils.UiState

@Composable
fun TestScreen(
    viewModel: TestViewModel = hiltViewModel()
) {

    val responseText by viewModel.text.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    var isLoading by remember {
        mutableStateOf(false)
    }
    var requestText by remember {
        mutableStateOf("")
    }

    when (uiState) {
        is UiState.Loading -> {
            isLoading = (uiState as UiState.Loading).inProgress
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
                // подключть едит текст, добавить кнопку, при нажатии кидадть запрос в репу, делаить, выводить новый текст в текст поле
            MyPrettyTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                onTextChange = {
                   requestText = it
                },
                hint = "Type some text"
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                onClick = {
                    viewModel.startRequest(requestText)

                }) {
                Text(text = "Start request")
            }

            if (isLoading) {
                Loader(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                text = responseText
            )
        }
    }
}

@Composable
fun MyPrettyTextField(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    onTextChange: (String) -> Unit = {},
    hint: String = ""
) {
    /**
     * вот этот ремембор то и делает что запоминает что происходит внутри перменной
     * если вдруг так случилось получилось что она изменилась, произоойдет та самай рекомпозиция
     * дернется перменная, и все что от нее зависит перерисуется
     */
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    var isSelected by remember {
        mutableStateOf(false)
    }
    /**
     * кто бы мог подумать что анимировать на столько просто
     */
    val borderColor by animateColorAsState(if (isSelected) Color.DarkGray else Color.Transparent)

    Card(
        modifier = modifier,
        shape = shape,
        border = BorderStroke(1.dp, borderColor),
        elevation = 2.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
        ) {
            BasicTextField(
                value = text,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                onValueChange = {
                    text = it
                    onTextChange(it)
                    isHintDisplayed = it.isEmpty()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isSelected = it.isFocused
                    }
            )
            if (isHintDisplayed) {
                Text(
                    text = hint,
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun Loader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .width(40.dp)
                .height(40.dp),
            strokeWidth = 4.dp
        )
    }
}

@Preview
@Composable
fun MyPrettyTextFieldPreview() {
    MyPrettyTextField(
        modifier = Modifier.fillMaxWidth(),
        hint = "Type some text"
    )
}