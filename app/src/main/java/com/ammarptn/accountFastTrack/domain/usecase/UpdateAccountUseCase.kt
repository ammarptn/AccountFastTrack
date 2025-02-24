package com.ammarptn.accountFastTrack.domain.usecase

import com.ammarptn.accountFastTrack.data.local.AccountEntity
import com.ammarptn.accountFastTrack.domain.repository.AccountRepository
import javax.inject.Inject

class UpdateAccountUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(account: AccountEntity) {
        repository.updateAccount(account)
    }
}