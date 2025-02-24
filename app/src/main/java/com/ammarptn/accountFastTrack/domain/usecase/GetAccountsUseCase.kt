package com.ammarptn.accountFastTrack.domain.usecase

import com.ammarptn.accountFastTrack.data.local.AccountEntity
import com.ammarptn.accountFastTrack.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    operator fun invoke(): Flow<List<AccountEntity>> = repository.getAllAccounts()
}