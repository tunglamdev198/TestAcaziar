package com.lamnt.testacaziasoft.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataUser (
	@Expose
	@SerializedName("user") val user : User
)