package com.lamnt.testacaziasoft.repository.impl

import com.lamnt.testacaziasoft.models.Response
import com.lamnt.testacaziasoft.network.ApiService
import com.lamnt.testacaziasoft.repository.UsersRepository
import io.reactivex.Observable
import javax.inject.Inject

class UsersImpl @Inject constructor(private val apiService: ApiService):
    UsersRepository {
    override fun getRandomUser(page: Int): Observable<Response> {
        return apiService.getRandomUsers()
    }
}