package com.ammarptn.accountFastTrack.domain.repository

import com.ammarptn.accountFastTrack.data.local.AccountEntity
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getAllAccounts(): Flow<List<AccountEntity>>
    suspend fun getAccount(accountId: Int): AccountEntity?
    suspend fun insertAccount(account: AccountEntity)
    suspend fun updateAccount(account: AccountEntity)
    suspend fun deleteAccount(accountId: Int)
}