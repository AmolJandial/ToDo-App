package com.yepyuno.todolist.data.local.model.auth

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "userTable")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @SerializedName("user")
    var username: String?,

    @SerializedName("password")
    var password: String?,

    @SerializedName("isSynced")
    var isSynced: Boolean?
)