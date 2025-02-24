package com.ammarptn.accountFastTrack.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ammarptn.accountFastTrack.data.local.AccountEntity
import com.ammarptn.accountFastTrack.domain.usecase.GetAccountUseCase
import com.ammarptn.accountFastTrack.domain.usecase.UpdateAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAccountViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state = mutableStateOf<EditAccountState>(EditAccountState.Loading)

    private val accountId: Int = checkNotNull(savedStateHandle["accountId"])

    fun loadAccountData() {
        viewModelScope.launch {
            try {
                state.value = EditAccountState.Loading
                state.value = EditAccountState.Success(getAccountUseCase(cardId = accountId))
            } catch (e: Exception) {
                state.value = EditAccountState.Error(e.localizedMessage)

            }
        }
    }

    fun updateAccount(accountEntity: AccountEntity) {
        viewModelScope.launch {
            updateAccountUseCase(account = accountEntity.copy(id = accountId))
        }
    }


}

sealed class EditAccountState {
    data class Success(val data: AccountEntity?) : EditAccountState()

    class Error(val message: String) : EditAccountState()

    data object Loading : EditAccountState()

}
