package com.atsapp.pixabaytestapp.utils

//Clase para definir las constantes de la aplicación
class Const {

    companion object {
        val PREFERENCE_NAME = "USER_DATA"
        val API_URL = "https://pixabay.com/api/"
        val API_KEY = "YOU-APIKEY"
    }

    class LocalData {
        companion object{
            val LAST_QUERY = "query"
            val LAST_LANG = "lang"
            val LAST_CATEGORY = "category"
        }
    }
}