package com.ammarptn.accountFastTrack.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface AccountDao {
    @Query("SELECT * FROM account")
    fun getAllAccounts(): Flow<List<AccountEntity>>

    @Query("SELECT * FROM account WHERE `id` = :accountId")
    suspend fun getAccount(accountId: Int): AccountEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: AccountEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAccount(account: AccountEntity)

    @Query("DELETE FROM account WHERE `id` = :accountId")
    suspend fun deleteAccount(accountId: Int)
}