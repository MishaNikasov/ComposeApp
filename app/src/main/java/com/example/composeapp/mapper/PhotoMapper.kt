package com.example.composeapp.mapper

import com.example.composeapp.data.model.PhotosResponseModel
import com.example.composeapp.ui.entity.ImageSize
import com.example.composeapp.ui.entity.PhotoEntity
import com.example.composeapp.utils.EntityMapper
import javax.inject.Inject

class PhotoMapper @Inject constructor(): EntityMapper<PhotosResponseModel, PhotoEntity> {
    override fun mapFromModel(model: PhotosResponseModel?): PhotoEntity {
        return PhotoEntity(
            id = model?.id ?: "",
            description = model?.alt_description ?: "",
            image = ImageSize(
                full = model?.urls?.full ?: "",
                raw = model?.urls?.raw ?: "",
                regular = model?.urls?.regular ?: "",
                small = model?.urls?.small ?: "",
                thumb =model?.urls?.thumb ?: "",
            )
        )
    }
}