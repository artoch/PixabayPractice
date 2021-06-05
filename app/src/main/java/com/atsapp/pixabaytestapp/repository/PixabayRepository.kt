package com.atsapp.pixabaytestapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.atsapp.pixabaytestapp.BaseApp.Companion.sessionManager
import com.atsapp.pixabaytestapp.data.models.ResponseAllImage
import com.atsapp.pixabaytestapp.data.remote.data_source.PixDataSource
import com.atsapp.pixabaytestapp.utils.NetworkState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PixabayRepository @Inject constructor(private val pixDataSource: PixDataSource) {

    var myLastQuery = ""
    var myLastCategory = ""


    //Obtener la data
    fun getAllPictures(query:String = "", category:String ="", lang:String = ""): LiveData<ResponseAllImage> {
        if (pixDataSource.searchMyPictures.value == null ||
                (myLastQuery != sessionManager.getQuery() || myLastCategory!= sessionManager.getCategory())) {
            myLastQuery = query
            myLastCategory = category
            pixDataSource.myPixaImagesList(query, category, lang)
        }

        return pixDataSource.searchMyPictures
    }

    //NetWork state.

    fun getNetworkState(): LiveData<NetworkState> = pixDataSource.networkState

}