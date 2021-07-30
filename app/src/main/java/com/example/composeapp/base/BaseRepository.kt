package com.example.composeapp.base

import com.example.composeapp.utils.DataState
import com.example.composeapp.utils.ErrorModel
import retrofit2.Response

open class BaseRepository {
    fun <Model> startRequest(response: Response<Model?>, successesAction: ((Model?) -> (Unit))? = null): DataState<Model> {
        try {
            if (response.isSuccessful) {
                response.body()?.let { model ->
                    successesAction?.invoke(model)
                    return DataState.Success(model)
                }
                return DataState.Error(ErrorModel(response))
            } else {
                return DataState.Error(ErrorModel(response))
            }
        } catch (e: Exception) {
            return DataState.Error(ErrorModel(response))
        }
    }
}

