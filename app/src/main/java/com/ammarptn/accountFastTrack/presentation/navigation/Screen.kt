package com.ammarptn.accountFastTrack.presentation.navigation

sealed class Screen(val route: String) {
    object Cards : Screen("cards")
    object AddCard : Screen("add_card")
}