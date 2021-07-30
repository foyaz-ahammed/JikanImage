package com.walmart.jikanimage.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walmart.jikanimage.repository.JikanRepository
import com.walmart.jikanimage.repository.entities.DataResult
import com.walmart.jikanimage.repository.entities.Response
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ImagesViewModel(private val repository: JikanRepository): ViewModel() {
    private val _images = MutableLiveData< DataResult<List<Response.ImageData>> >()
    val images: LiveData< DataResult<List<Response.ImageData>> >
        get() = _images

    fun loadImages(query: String) {
        viewModelScope.launch {
            repository.searchImages(query)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                            result -> _images.postValue(DataResult(result.imageList, null))
                    },
                    {
                            error -> _images.postValue(DataResult(null, error))
                    }
                )
        }
    }
}