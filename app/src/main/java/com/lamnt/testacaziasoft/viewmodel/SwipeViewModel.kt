package com.lamnt.testacaziasoft.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lamnt.testacaziasoft.models.Response
import com.lamnt.testacaziasoft.models.User
import com.lamnt.testacaziasoft.repository.PrefRepository
import com.lamnt.testacaziasoft.repository.UsersRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.reflect.Type


class SwipeViewModel @ViewModelInject constructor(
    private val gson: Gson,
    private val prefRepository: PrefRepository,
    private val userRepository: UsersRepository,
    @ApplicationContext private val context: Context
) : BaseViewModel() {
    val usersLiveData: MutableLiveData<ArrayList<User>> = MutableLiveData()
    fun getListUser(page: Int) {
        userRepository.getRandomUser(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Response> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    addDispose(d)
                }

                override fun onNext(t: Response) {
                    usersLiveData.postValue(t.results)
                    Log.d("AAA", "onNext: " + t.results.toString())
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    Log.d("AAA", "onNext: " + e.message)
                }

            })
    }

    fun getListUserOffline(): ArrayList<User> {
        val dataUser = prefRepository.getString("dataUser")
        if (dataUser.isEmpty()) {
            return ArrayList()
        }
        val listType: Type = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(dataUser, listType)
    }

    fun addUserOffline(user: User) {
        var userOffline = getListUserOffline()
        userOffline.add(user)
        prefRepository.putList("dataUser", userOffline)
    }


}