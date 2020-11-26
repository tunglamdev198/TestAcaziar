package com.lamnt.testacaziasoft.repository

import com.lamnt.testacaziasoft.models.Response
import io.reactivex.Observable

interface UsersRepository {
    fun getRandomUser(page : Int) : Observable<Response>
}