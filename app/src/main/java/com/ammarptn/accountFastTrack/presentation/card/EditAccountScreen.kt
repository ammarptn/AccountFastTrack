package com.ammarptn.accountFastTrack.presentation.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ammarptn.accountFastTrack.R
import com.ammarptn.accountFastTrack.data.local.AccountEntity
import com.ammarptn.accountFastTrack.presentation.EditAccountState
import com.ammarptn.accountFastTrack.presentation.EditAccountViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountScreen(
    onNavigateBack: () -> Unit,
    viewModel: EditAccountViewModel = hiltViewModel()
) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var environment by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(0xFF1976D2) } // Default blue color
    var passwordVisible by remember { mutableStateOf(false) }

    var usernameInputError by remember { mutableStateOf<String?>(null) }
    var passwordInputError by remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit) {
        viewModel.loadAccountData()
    }

    fun validateUsername(username: String): Boolean {
        return when {
            username.isBlank() -> {
                usernameInputError = "Username is required"
                false
            }

            else -> {
                usernameInputError = null
                true
            }
        }
    }

    fun validatePassword(password: String): Boolean {
        return when {
            password.isBlank() -> {
                passwordInputError = "Password is required"
                false
            }

            else -> {
                passwordInputError = null
                true
            }
        }
    }

    fun calculatePasswordStrength(password: String): Float {
        if (password.isEmpty()) return 0f
        var strength = 0
        if (password.length >= 8) strength++
        if (password.any { it.isDigit() }) strength++
        if (password.any { it.isLetter() }) strength++
        if (password.any { !it.isLetterOrDigit() }) strength++
        return strength / 4f
    }


    when (viewModel.state.value) {
        is EditAccountState.Error -> {

        }

        EditAccountState.Loading -> {

        }

        is EditAccountState.Success -> {
            val account = (viewModel.state.value as EditAccountState.Success).data
            LaunchedEffect(account) {
                username = account?.username ?: ""
                password = account?.password ?: ""
                environment = account?.environment ?: ""
                selectedColor = account?.cardColor ?: Color.Gray.value.toLong()
            }

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Edit Account") },
                        navigationIcon = {
                            IconButton(onClick = onNavigateBack) {
                                Icon(
                                    painterResource(id = R.drawable.ic_back),
                                    contentDescription = "Go back"
                                )
                            }
                        },
                        actions = {
                        }
                    )
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding)
                        .imePadding()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CardPreview(selectedColor, username, password, environment)
                    Text(
                        text = "Card Color",
                        style = MaterialTheme.typography.titleMedium
                    )
                    CardColorPicker(
                        selectedColor = selectedColor,
                        onColorSelected = { selectedColor = it }
                    )

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = usernameInputError != null,
                        supportingText = usernameInputError?.let { { Text(it) } }
                    )

                    Column(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            isError = passwordInputError != null,
                            supportingText = passwordInputError?.let { { Text(it) } },
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        painter = painterResource(
                                            id = if (passwordVisible) {
                                                R.drawable.ic_visibility_off
                                            } else {
                                                R.drawable.ic_visibility
                                            }
                                        ),
                                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        )
                        
                        if (password.isNotEmpty()) {
                            val strength = calculatePasswordStrength(password)
                            LinearProgressIndicator(
                                progress = { strength },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                color = when {
                                    strength < 0.3f -> MaterialTheme.colorScheme.error
                                    strength < 0.7f -> MaterialTheme.colorScheme.error
                                    else -> MaterialTheme.colorScheme.primary
                                },
                            )
                            Text(
                                text = when {
                                    strength < 0.3f -> "Weak password"
                                    strength < 0.7f -> "Medium password"
                                    else -> "Strong password"
                                },
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 4.dp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    OutlinedTextField(
                        value = environment,
                        onValueChange = { environment = it },
                        label = { Text("Environment") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )


                    Button(
                        onClick = {
                            if (validateUsername(username) &&
                                validatePassword(password)
                            ) {
                                viewModel.updateAccount(
                                    AccountEntity(
                                        username = username,
                                        password = password,
                                        environment = environment,
                                        cardColor = selectedColor
                                    )
                                ).let {
                                    onNavigateBack()
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Update Account")
                    }
                }
            }

        }
    }
}
