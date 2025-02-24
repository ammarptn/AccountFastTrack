package com.ammarptn.accountFastTrack.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class AccountEntity(
    @PrimaryKey val id: Int? = null,
    val username: String,
    val password: String,
    val environment: String,
    val cardColor: Long
)