package com.example.yuelinghui.rxjavasample.ui

import androidx.lifecycle.ViewModel
import com.example.yuelinghui.rxjavasample.persistence.User
import com.example.yuelinghui.rxjavasample.persistence.UserDao
import io.reactivex.Completable
import io.reactivex.Flowable

class UserViewModel(private val dataSource: UserDao) : ViewModel() {


    fun getUserName(id:String): Flowable<String> = dataSource.getUserById(id).map {
        it.userName
    }

    fun updateUserName(userId:String,userName: String): Completable {
        return Completable.fromAction {
           val user = User(userId,userName)
            dataSource.insertUser(user)
        }
    }
}