package com.ammarptn.accountFastTrack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AccountEntity::class],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
abstract class AccountDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}