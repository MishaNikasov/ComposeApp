package com.example.composeapp.utils

import retrofit2.Response

class ErrorModel (response: Response<*>? = null) {

    var code: Int = -1
    var messageText: String = ""
    var requestUrl: String = ""

    companion object {
        private fun noConnectionError(): ErrorModel {
            val errorModel = ErrorModel()
            errorModel.messageText = "No internet connection"
            return errorModel
        }

        fun throwableError(throwable: Throwable): ErrorModel {
            return if (throwable.localizedMessage?.contains("Unable to resolve host") == true) {
                noConnectionError()
            } else {
                val errorModel = ErrorModel()
                errorModel.messageText = throwable.localizedMessage ?: "Error"
                errorModel
            }
        }
    }

    init {
        response?.let {
            code = it.raw().code
            messageText = it.raw().message
            requestUrl = it.raw().request.url.toString()
        }
    }

    override fun toString(): String {
        return if (code == -1)
            messageText
        else
            "$code: $messageText\n$requestUrl"
    }
}