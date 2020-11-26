package com.lamnt.testacaziasoft.repository.impl

import android.content.SharedPreferences
import com.google.gson.Gson
import com.lamnt.testacaziasoft.repository.PrefRepository
import javax.inject.Inject


class PrefImpl @Inject constructor(
    private val gson: Gson,
    private val preferences: SharedPreferences.Editor,
    private val sharedPreferences: SharedPreferences
) : PrefRepository {
    override fun putString(key: String, value: String) {
        preferences.putString(key, value).apply()
    }

    override fun <T> putList(key: String, data: List<T>) {
        val jsonObject = gson.toJson(data)
        putString(key, jsonObject)
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "").toString()
    }

    override fun putBoolean(key: String, value: Boolean) {
        preferences.putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun clear() {
        preferences.clear().apply()
    }
}