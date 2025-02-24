package com.ammarptn.accountFastTrack.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ammarptn.accountFastTrack.data.local.AccountEntity
import com.ammarptn.accountFastTrack.domain.usecase.AddAccountUseCase
import com.ammarptn.accountFastTrack.domain.usecase.DeleteAccountUseCase
import com.ammarptn.accountFastTrack.domain.usecase.GetAccountsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val addAccountUseCase: AddAccountUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ShortcutState())
    val state = _state.asStateFlow()

    init {
        loadCards()
    }

    private fun loadCards() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                getAccountsUseCase().collect { cards ->
                    _state.update {
                        it.copy(
                            accounts = cards,
                            isLoading = false,
                            error = null
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Failed to load cards: ${e.localizedMessage}"
                    )
                }
            }
        }
    }

    fun addAccount(card: AccountEntity) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                addAccountUseCase(card)
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Failed to add card: ${e.localizedMessage}"
                    )
                }
            }
        }
    }

    fun deleteCard(card: AccountEntity) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                card.id?.let { deleteAccountUseCase(it) }
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Failed to delete card: ${e.localizedMessage}"
                    )
                }
            }
        }
    }

    fun clearError() {
        _state.update { it.copy(error = null) }
    }
}
data class ShortcutState(
    val accounts: List<AccountEntity> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)