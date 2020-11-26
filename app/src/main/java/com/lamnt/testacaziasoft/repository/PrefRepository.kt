package com.lamnt.testacaziasoft.repository

interface PrefRepository {
    fun putString(key: String, value : String)
    fun <T> putList(key: String, data : List<T>)
    fun getString(key: String) : String
    fun putBoolean(key: String, value : Boolean)
    fun getBoolean(key: String) : Boolean
    fun clear()
}