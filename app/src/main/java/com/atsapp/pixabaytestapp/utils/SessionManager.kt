package com.atsapp.pixabaytestapp.utils

import android.content.Context
import com.atsapp.pixabaytestapp.utils.Const.Companion.PREFERENCE_NAME

class SessionManager (context: Context) {
    private val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    private val editor = preferences.edit()

    //Local data
    fun setQuery(query:String = ""){
        editor.putString(Const.LocalData.LAST_QUERY, query)
        editor.putString(Const.LocalData.LAST_CATEGORY, "")
        editor.apply()
    }

    fun getQuery():String = preferences.getString(Const.LocalData.LAST_QUERY,"")!!

    fun setCategory(category:String = ""){
        editor.putString(Const.LocalData.LAST_CATEGORY, category)
        editor.putString(Const.LocalData.LAST_QUERY, "")
        editor.apply()
    }

    fun getCategory():String = preferences.getString(Const.LocalData.LAST_CATEGORY,"")!!

}