package com.example.composeapplication.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.composeapplication.compose.ui.ComposeBottomBar
import com.example.composeapplication.compose.ui.ComposeNavHost
import com.example.composeapplication.compose.ui.ComposeNavigationRail
import org.koin.androidx.compose.koinViewModel
import com.example.composeapplication.ui.theme.Red30TechTheme
import com.example.composeapplication.compose.data.ConferenceRepository
import com.example.composeapplication.compose.data.SessionInfo
import com.example.composeapplication.compose.data.fake
import com.example.composeapplication.compose.data.fake2
import com.example.composeapplication.compose.data.fake3
import com.example.composeapplication.compose.data.fake4


@Composable
fun MyCompose(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel =  koinViewModel<MainViewModel>()) {
    Red30TechTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val snackbarHostState = remember { SnackbarHostState() }

        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val navigationType = windowSizeClass.navigationType

        Scaffold(
            bottomBar = {
                if (navigationType == NavigationType.BOTTOM_NAVIGATION) {
                    ComposeBottomBar(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) { innerPadding ->
            Row {
                if (navigationType == NavigationType.RAIL) {
                    ComposeNavigationRail(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
                ComposeNavHost(
                    modifier = modifier.padding(innerPadding),
                    navController = navController,
                    snackbarHostState = snackbarHostState,
                    viewModel = viewModel
                )
            }
        }
    }
}

enum class NavigationType{
    BOTTOM_NAVIGATION,
    RAIL
}

val WindowSizeClass.navigationType: NavigationType
    get() = when (windowWidthSizeClass){
        WindowWidthSizeClass.EXPANDED -> NavigationType.RAIL
        else -> NavigationType.BOTTOM_NAVIGATION
    }

@Preview(showBackground = true)
@Composable
fun MyComposePreview() {
    val viewModel = MainViewModel(
        saveStateHandle = SavedStateHandle(),
        conferenceRepository = FakeConferenceRepository()
    )
    MyCompose(viewModel = viewModel)
}

private class FakeConferenceRepository: ConferenceRepository {
    override suspend fun loadConferenceInfo(): List<SessionInfo> {
       return listOf(
           SessionInfo.fake(),
           SessionInfo.fake2(),
           SessionInfo.fake3(),
           SessionInfo.fake4()
       )
    }

    override suspend fun toggleFavorite(sessionId: Int): List<Int> {
        TODO("Not yet implemented")
    }

}