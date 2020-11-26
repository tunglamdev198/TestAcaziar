package com.lamnt.testacaziasoft.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class Location (
	@Expose
	@SerializedName("city") val city : String,
	@Expose
	@SerializedName("state") val state : String,
	@Expose
	@SerializedName("zip") val zip : Int
)