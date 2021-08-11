package com.example.composeapp.ui.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TestScreen(
    viewModel: TestViewModel = hiltViewModel()
) {

    val responseText by viewModel.text.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
                // подключть едит текст, добавить кнопку, при нажатии кидадть запрос в репу, делаить, выводить новый текст в текст поле
            MyPrettyTextField(modifier = Modifier.fillMaxWidth())
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                onClick = {
                    viewModel.startRequest("")

                }) {
                Text(text = "Start request")
            }
            Text(text = responseText)

        }
    }
}

@Composable
fun MyPrettyTextField(
    modifier: Modifier = Modifier,
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

    Card(
        modifier = modifier
    ) {
        Box {
            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                    isHintDisplayed = it.isEmpty()
                }
            )
            if (isHintDisplayed) {
                Text(text = hint)
            }
        }
    }
}