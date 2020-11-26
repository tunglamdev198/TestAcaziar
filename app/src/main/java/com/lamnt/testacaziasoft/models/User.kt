package com.lamnt.testacaziasoft.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose @SerializedName("gender") val gender: String,
    @Expose @SerializedName("name") val name: Name,
    @Expose @SerializedName("location") val location: Location,
    @Expose @SerializedName("email") val email: String,
    @Expose @SerializedName("username") val username: String,
    @Expose @SerializedName("password") val password: String,
    @Expose @SerializedName("salt") val salt: String,
    @Expose @SerializedName("md5") val md5: String,
    @Expose @SerializedName("sha1") val sha1: String,
    @Expose @SerializedName("sha256") val sha256: String,
    @Expose @SerializedName("phone") val phone: String,
    @Expose @SerializedName("cell") val cell: String,
    @Expose @SerializedName("TFN") val tFN: Int,
    @Expose @SerializedName("dob") val dob: Dob,
    @Expose @SerializedName("picture") val picture: Picture
)