package com.walmart.jikanimage.repository

import com.walmart.jikanimage.helper.DEFAULT_KEYWORD

/**
 * Repository class
 */
class JikanRepository(private val jikanAPI: JikanAPI) {
    fun searchImages(query: String) = jikanAPI.searchImages(if(query.isEmpty()) DEFAULT_KEYWORD else query)
}