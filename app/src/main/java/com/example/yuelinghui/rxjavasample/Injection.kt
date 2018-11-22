package com.example.yuelinghui.rxjavasample

import android.content.Context
import com.example.yuelinghui.rxjavasample.persistence.UserDao
import com.example.yuelinghui.rxjavasample.persistence.UserDatabase
import com.example.yuelinghui.rxjavasample.ui.UserModelFactory

object Injection {

    fun provideUserDataSource(context: Context): UserDao {
        val database = UserDatabase.getInstance(context)
        return database.userDao()
    }

    fun provideUserModelFactory(context: Context): UserModelFactory {
        val dataSource = provideUserDataSource(context)
        return UserModelFactory(dataSource)
    }
}