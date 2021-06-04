package com.atsapp.pixabaytestapp.ui.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.atsapp.pixabaytestapp.data.models.ResponseAllImage
import com.atsapp.pixabaytestapp.repository.PixabayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val pixabayRepository: PixabayRepository) : ViewModel(){

    val myPictures by lazy {
        getAllPicture("","")
    }


    // Companies Data
    fun getAllPicture(query:String = "", category: String = "", lang:String = ""): LiveData<ResponseAllImage> {
        return pixabayRepository.getAllPictures(query, category, lang)
    }
    val networkState = pixabayRepository.getNetworkState()

}