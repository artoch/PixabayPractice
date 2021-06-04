package com.atsapp.pixabaytestapp.aux_interface.utils
//Clase generica para hacer clicks a los adapters
interface AdapterOnClick<T> {
    fun adapterOnClick(item:T, pos:Int = 0)
}