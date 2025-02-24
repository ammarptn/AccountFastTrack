package com.ammarptn.accountFastTrack.domain.usecase

import com.ammarptn.accountFastTrack.domain.repository.AccountRepository
import javax.inject.Inject


class DeleteAccountUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(accountId: Int) = repository.deleteAccount(accountId)
}