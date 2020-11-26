package com.lamnt.testacaziasoft.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Picture (

	@Expose @SerializedName("large") val large : String,
	@Expose @SerializedName("medium") val medium : String,
	@Expose @SerializedName("thumbnail") val thumbnail : String
)