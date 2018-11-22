package com.example.yuelinghui.rxjavasample.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM Users LIMIT 1")
    fun getUser():Flowable<User>

    @Query("SELECT * FROM Users WHERE userid = :id")
    fun getUserById(id:String):Flowable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("DELETE FROM Users")
    fun deleteAllUsers()
}