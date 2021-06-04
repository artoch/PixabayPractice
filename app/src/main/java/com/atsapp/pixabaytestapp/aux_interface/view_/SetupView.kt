package com.atsapp.pixabaytestapp.aux_interface.view_
//Clase generica para reducir el biporlate
interface SetupView {

    val TAG: String

    fun setupView(){
        setupBindings()
        setupObservers()
    }

    //CONFIGURAR LOS OBSERVADORES
    fun setupObservers()

    //CONDIFURAR LOS BINDINGS (CARACTERISTICA DE LA VISTA)
    fun setupBindings()

}