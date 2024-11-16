  package net.adhikary.mrtbuddy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

sealed class Screen {
    object Home : Screen()
    object Calculator : Screen()
    object History : Screen()
    object More : Screen()
    data class TransactionList(val cardIdm: String) : Screen()
    object OpenSourceLicenses : Screen()
}

class NavController(
    private val initialScreen: Screen,
    private val backStackState: MutableState<List<Screen>>
) {
    val currentScreen: Screen
        get() = backStackState.value.last()

    fun navigate(route: Screen) {
        backStackState.value = backStackState.value + route
    }

    fun navigateBack(): Boolean {
        if (backStackState.value.size > 1) {
            backStackState.value = backStackState.value.dropLast(1)
            return true
        }
        return false
    }
}

@Composable
fun rememberNavController(
    initialScreen: Screen = Screen.Home
): NavController {
    val backStackState = rememberSaveable { mutableStateOf(listOf(initialScreen)) }
    return remember { NavController(initialScreen, backStackState) }
}
