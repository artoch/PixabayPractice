package com.atsapp.pixabaytestapp.data.remote.data_source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.atsapp.pixabaytestapp.data.models.ResponseAllImage
import com.atsapp.pixabaytestapp.data.remote.api.PixabayApi
import com.atsapp.pixabaytestapp.utils.NetworkState
import com.atsapp.pixabaytestapp.utils.loge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PixDataSource @Inject constructor(private val pixabayApi: PixabayApi){
    //Para gestionar el estado de conexión
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get () = _networkState

    //Para obtener la data!
    private val _searchMyPictures = MutableLiveData<ResponseAllImage>()
    val searchMyPictures: LiveData<ResponseAllImage>
        get () = _searchMyPictures

    fun myPixaImagesList(query:String = "", category:String = "", lang:String = ""){
        _networkState.postValue(NetworkState.LOADING)
        GlobalScope.launch(Dispatchers.IO) {
            val response = pixabayApi.getAllImages(query, category, lang)
            if (response.isSuccessful){
                if (response.body()!=null){
                    _networkState.postValue(NetworkState.LOADED)
                    val data = response.body()!!
                    _searchMyPictures.postValue(data)
                }
            }else{
                _networkState.postValue(NetworkState.ERROR)
                loge("ERROR EN OBTENER TODOS LOS COMPANIES")
            }
        }
    }


}