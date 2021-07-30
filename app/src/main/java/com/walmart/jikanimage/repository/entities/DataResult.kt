    package com.walmart.jikanimage.repository.entities

/**
 * Used in model class of livedata
 */
class DataResult<T> (
    val data: T? = null,            //Result
    val error: Throwable? = null,   //Error
) {
    fun success() = data != null
}