package com.ammarptn.accountFastTrack.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    var showBackupDialog by remember { mutableStateOf(false) }
    var showRestoreDialog by remember { mutableStateOf(false) }
    var biometricEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Backup Option
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showBackupDialog = true }
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Backup to JSON",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Export your accounts to a JSON file",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Restore Option
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showRestoreDialog = true }
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Restore from JSON",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Import your accounts from a JSON file",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Biometric Option
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Enable Biometric",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Use fingerprint or face recognition to secure your accounts",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = biometricEnabled,
                    onCheckedChange = { enabled ->
                        biometricEnabled = enabled
                        // TODO: Implement biometric toggle
                    }
                )
            }
        }

        // Backup Dialog
        if (showBackupDialog) {
            AlertDialog(
                onDismissRequest = { showBackupDialog = false },
                title = { Text("Backup Accounts") },
                text = { Text("This will export all your accounts to a JSON file. Continue?") },
                confirmButton = {
                    Button(
                        onClick = {
                            // TODO: Implement backup
                            showBackupDialog = false
                        }
                    ) {
                        Text("Backup")
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = { showBackupDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        // Restore Dialog
        if (showRestoreDialog) {
            AlertDialog(
                onDismissRequest = { showRestoreDialog = false },
                title = { Text("Restore Accounts") },
                text = { Text("This will import accounts from a JSON file. This will replace all existing accounts. Continue?") },
                confirmButton = {
                    Button(
                        onClick = {
                            // TODO: Implement restore
                            showRestoreDialog = false
                        }
                    ) {
                        Text("Restore")
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = { showRestoreDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
