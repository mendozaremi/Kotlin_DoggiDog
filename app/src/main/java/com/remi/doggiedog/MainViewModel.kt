package com.remi.doggiedog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remi.doggiedog.network.DogImage
import com.remi.doggiedog.network.DogImageApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // This image will change needs to be mutable
    private val _currentlyDisplayedImage = MutableLiveData<DogImage>()
    val currentlyDisplayedImage: LiveData<DogImage> = _currentlyDisplayedImage

    init {
        getNewDog()
    }

    fun getNewDog() {
        viewModelScope.launch {
            // get first item in list from response
            _currentlyDisplayedImage.value = DogImageApi.retrofitService.getRandomDogImage()
        }
    }
}