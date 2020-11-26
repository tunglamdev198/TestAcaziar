package com.lamnt.testacaziasoft.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(@Expose @SerializedName("results") val results : ArrayList<User>)