package com.ammarptn.accountFastTrack.presentation

import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ammarptn.accountFastTrack.presentation.card.AddAccountScreen
import com.ammarptn.accountFastTrack.presentation.card.AccountListScreen
import com.ammarptn.accountFastTrack.presentation.card.EditAccountScreen
import com.ammarptn.accountFastTrack.presentation.settings.SettingsScreen
import com.ammarptn.accountFastTrack.ui.theme.AccountFastTrackTheme
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AccountFastTrackTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Shortcuts.route
                ) {
                    composable(Screen.Shortcuts.route) {
                        AccountListScreen(
                            onNavigateToAdd = {
                                navController.navigate(Screen.AddShortcut.route)
                            },
                            onNavigateToEdit = {
                                navController.navigate(Screen.EditAccount.createRoute(it))
                            },
                            onNavigateToSettings = {
                                navController.navigate(Screen.Settings.route)
                            },
                        )
                    }
                    composable(Screen.AddShortcut.route) {
                        AddAccountScreen(
                            onNavigateBack = {
                                navController.popBackStack()
                            },
                        )
                    }

                    composable(Screen.EditAccount.route, arguments = listOf(
                        navArgument("accountId") {
                            type = NavType.IntType
                        }
                    )) {
                        EditAccountScreen(
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(Screen.Settings.route) {
                        SettingsScreen(onNavigateBack = {
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }

}


sealed class Screen(val route: String) {
    object Shortcuts : Screen("shortcuts")
    object AddShortcut : Screen("add_shortcut")
    object Settings : Screen("setting")
    object EditAccount : Screen("edit_account/{accountId}") {
        fun createRoute(accountId: Int) = "edit_account/$accountId"
    }
}