package com.lamnt.testacaziasoft.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Dob(@Expose
               @SerializedName("date") val date : String,
               @Expose
               @SerializedName("age") val age : String)