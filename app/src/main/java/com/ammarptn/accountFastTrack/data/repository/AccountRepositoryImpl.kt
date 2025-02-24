package com.ammarptn.accountFastTrack.data.repository

import com.ammarptn.accountFastTrack.data.local.AccountDao
import com.ammarptn.accountFastTrack.data.local.AccountEntity
import com.ammarptn.accountFastTrack.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val dao: AccountDao
) : AccountRepository {

    override fun getAllAccounts(): Flow<List<AccountEntity>> {
        return dao.getAllAccounts()
    }

    override suspend fun getAccount(accountId: Int): AccountEntity? {
        return dao.getAccount(accountId)
    }

    override suspend fun insertAccount(account: AccountEntity) {
        dao.insertAccount(account)
    }

    override suspend fun updateAccount(account: AccountEntity) {
        dao.updateAccount(account)
    }

    override suspend fun deleteAccount(accountId: Int) {
        dao.deleteAccount(accountId)
    }

}