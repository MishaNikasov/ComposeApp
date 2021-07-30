package com.example.composeapp.mapper

import com.example.composeapp.data.model.search.SearchResultResponseModel
import com.example.composeapp.ui.entity.SearchEntity
import com.example.composeapp.utils.EntityMapper
import javax.inject.Inject

class SearchMapper @Inject constructor(
    private val photoMapper: PhotoMapper
): EntityMapper<SearchResultResponseModel, SearchEntity> {
    override fun mapFromModel(model: SearchResultResponseModel?): SearchEntity {
        return SearchEntity(
            results = photoMapper.mapFromModelList(model?.results),
            total = model?.total ?: 0,
            totalPages = model?.total_pages ?: 0
        )
    }
}