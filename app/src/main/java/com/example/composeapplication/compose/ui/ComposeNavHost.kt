package com.example.composeapplication.compose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeapplication.compose.MainViewModel
import com.example.composeapplication.compose.ui.screen.FavoritesScreen
import com.example.composeapplication.compose.ui.screen.Screen
import com.example.composeapplication.compose.ui.screen.SessionDetailScreen
import com.example.composeapplication.compose.ui.screen.SessionsScreen
import com.example.composeapplication.compose.ui.screen.SpeakerScreen

@Composable
fun ComposeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    viewModel: MainViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedSession by viewModel.selectedSession.collectAsStateWithLifecycle()

    uiState.snackbarMessage?.let { snackbarMessage ->
        val message = stringResource(snackbarMessage)
        LaunchedEffect(snackbarHostState, snackbarMessage) {
            val result = snackbarHostState.showSnackbar(message)
            if (result == SnackbarResult.Dismissed){
                viewModel.showSnackbar()
            }
        }

    }

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.Sessions.route
    ) {
        composable(route = Screen.Sessions.route) {
            SessionsScreen(
                uiState = uiState,
                onSessionClick = { sessionId ->
                    viewModel.setSelectedSessionId(sessionId)
                    navController.navigate(Screen.SessionDetail.route)
                },
                onFavoriteClick = { sessionId ->
                    viewModel.toggleFavorite(sessionId)
                },
                onDayClick = { day ->
                    viewModel.setDay(day)
                }
            )
        }
        composable(route = Screen.Speakers.route) {
            SpeakerScreen(uiState = uiState)
        }
        composable(route = Screen.Favorites.route) {
            FavoritesScreen(
                uiState = uiState,
                onSessionClick = { sessionId ->
                    viewModel.setSelectedSessionId(sessionId)
                    navController.navigate(Screen.SessionDetail.route)
                },
                onFavoriteClick = { sessionId ->
                    viewModel.toggleFavorite(sessionId)
                }
            )
        }
        composable(route = Screen.SessionDetail.route) {
            selectedSession?.let { sessionInfo ->
                SessionDetailScreen(sessionInfo = sessionInfo)
            }
        }
    }
}