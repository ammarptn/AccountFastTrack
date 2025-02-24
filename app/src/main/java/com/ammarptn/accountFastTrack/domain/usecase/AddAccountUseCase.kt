package com.ammarptn.accountFastTrack.domain.usecase

import com.ammarptn.accountFastTrack.data.local.AccountEntity
import com.ammarptn.accountFastTrack.domain.repository.AccountRepository
import javax.inject.Inject

class AddAccountUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(account: AccountEntity) {
        require(account.username.isNotBlank()) { "username required" }
        require(account.password.isNotBlank()) { "password required" }
        require(account.environment.isNotBlank()) { "environment required" }

        repository.insertAccount(account)
    }
}