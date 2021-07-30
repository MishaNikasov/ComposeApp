package com.example.composeapp.utils

interface EntityMapper<Model, Entity> {

    fun mapFromModel(model: Model?): Entity

    fun mapFromModelList(modelList: List<Model?>?): List<Entity> {
        return modelList?.map { e -> mapFromModel(e) } ?: arrayListOf()
    }

}