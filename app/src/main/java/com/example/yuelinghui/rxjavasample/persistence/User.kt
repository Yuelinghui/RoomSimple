package com.example.yuelinghui.rxjavasample.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users")
data class User(@PrimaryKey
                @ColumnInfo(name = "userid")
                val userId: String = UUID.randomUUID().toString(),
                @ColumnInfo(name = "username")
                var userName: String)