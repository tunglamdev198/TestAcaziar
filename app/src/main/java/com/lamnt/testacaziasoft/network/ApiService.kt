package com.lamnt.testacaziasoft.network

import androidx.lifecycle.Observer
import com.lamnt.testacaziasoft.models.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/api?results=10")
    fun getRandomUsers() : Observable<Response>
}