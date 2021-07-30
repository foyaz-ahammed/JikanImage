package com.walmart.jikanimage.repository

import com.walmart.jikanimage.repository.entities.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API class
 *
 * @see searchImages
 */
interface JikanAPI {
    @GET("search/anime")
    fun searchImages(
        @Query("q") query: String
    ): Observable<Response.ImageList>
}