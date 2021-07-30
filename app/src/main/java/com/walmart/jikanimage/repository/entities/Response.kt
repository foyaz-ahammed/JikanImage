package com.walmart.jikanimage.repository.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response data type when getting data from network
 */
class Response {
    @JsonClass(generateAdapter = true)
    class ImageList (
        @Json(name = "results") val imageList: List<ImageData>
    )

    @JsonClass(generateAdapter = true)
    class ImageData (
        @Json(name = "mal_id") val id: Int,
        @Json(name = "image_url") val url: String,
        @Json(name = "title") val title: String
    )
}