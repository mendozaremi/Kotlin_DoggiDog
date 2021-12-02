package com.remi.doggiedog.viewmodels

import androidx.lifecycle.*
import com.remi.doggiedog.database.DogDao
import com.remi.doggiedog.database.DogEntity
import com.remi.doggiedog.network.DogImage
import com.remi.doggiedog.network.DogImageApi
import kotlinx.coroutines.launch

class MainViewModel(private val dogDao: DogDao) : ViewModel() {

    // This image will change needs to be mutable
    private val _currentlyDisplayedImage = MutableLiveData<DogImage>()
    val currentlyDisplayedImage: LiveData<DogImage> = _currentlyDisplayedImage


    init {
        getNewDog()
    }

    // get new dog
    fun getNewDog() {
        viewModelScope.launch {
            // get first item in list from response
            _currentlyDisplayedImage.value = DogImageApi.retrofitService.getRandomDogImage()
        }
    }
    // Add dog to database
    fun addDog(dogEntity: DogEntity){
        viewModelScope.launch {
            dogDao.addDogImage(dogEntity)
        }
    }
    // delete most recent dog from database
    fun deleteMostRecentDog(){
        viewModelScope.launch {
            dogDao.deleteDog()
        }
    }

    fun getAllDogs(): LiveData<List<DogEntity>>{
        return dogDao.getAllDogImages().asLiveData()
    }

    class MainViewModelFactory( private val dogDao: DogDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(dogDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }










}