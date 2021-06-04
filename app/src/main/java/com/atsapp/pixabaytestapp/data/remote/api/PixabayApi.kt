package com.atsapp.pixabaytestapp.data.remote.api

import com.atsapp.pixabaytestapp.data.models.ResponseAllImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    /**
     * @param q = busqueda (por defecto = "")
     * @param category = categorias (por defecto = "")
     * @param lang = IDIOMA (por defecto = "" (INGLES))
     * @author Alfredo Tochon
     * @return una lista de imagen en la clase -> ResponseAllImage
     */
    @GET("?")
    suspend fun getAllImages(@Query("q") query:String = "",
                             @Query("category") category:String = "",
                             @Query("lang") lang:String = ""): Response<ResponseAllImage>

}