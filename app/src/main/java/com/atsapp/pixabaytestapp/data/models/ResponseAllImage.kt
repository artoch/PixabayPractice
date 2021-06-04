package com.atsapp.pixabaytestapp.data.models


import com.google.gson.annotations.SerializedName

data class ResponseAllImage(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)