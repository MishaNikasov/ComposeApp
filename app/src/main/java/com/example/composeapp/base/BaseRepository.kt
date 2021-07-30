package com.example.composeapp.base

import com.example.composeapp.utils.DataState
import com.example.composeapp.utils.EntityMapper
import com.example.composeapp.utils.ErrorModel
import retrofit2.Response

open class BaseRepository {

    fun <Model, Entity> startRequest(
        response: Response<Model>,
        mapper: EntityMapper<Model, Entity>,
        rawResponseAction: ((Model?) -> (Unit))? = null
    ): DataState<Entity> {
        try {
            if (response.isSuccessful) {
                response.body()?.let { model ->
                    rawResponseAction?.invoke(model)
                    val mappedData = mapper.mapFromModel(model)
                    return DataState.Success(mappedData)
                }
                return DataState.Error(ErrorModel(response))
            } else {
                return DataState.Error(ErrorModel(response))
            }
        } catch (e: Exception) {
            return DataState.Error(ErrorModel(response))
        }
    }

    fun <Model, Entity> startArrayRequest(
        responseList: Response<List<Model>?>,
        mapper: EntityMapper<Model, Entity>,
        rawResponseAction: ((MutableList<Model>) -> (Unit))? = null
    ): DataState<MutableList<Entity>> {
        try {
            if (responseList.isSuccessful) {
                responseList.body()?.let { model ->
                    rawResponseAction?.invoke(model as MutableList)
                    val mappedData = mapper.mapFromModelList(model) as MutableList
                    return DataState.Success(mappedData)
                }
                return DataState.Error(ErrorModel(responseList))
            } else {
                return DataState.Error(ErrorModel(responseList))
            }
        } catch (e: Exception) {
            return DataState.Error(ErrorModel(responseList))
        }
    }
}