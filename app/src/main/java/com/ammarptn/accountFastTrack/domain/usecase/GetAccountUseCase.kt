package com.ammarptn.accountFastTrack.domain.usecase

import com.ammarptn.accountFastTrack.data.local.AccountEntity
import com.ammarptn.accountFastTrack.domain.repository.AccountRepository
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(cardId: Int): AccountEntity? = repository.getAccount(cardId)
}