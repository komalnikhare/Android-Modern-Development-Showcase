package com.example.composeapplication.compose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.R
import com.example.composeapplication.compose.ui.component.SessionItem
import com.example.composeapplication.compose.data.ConferenceDataUiState
import com.example.composeapplication.compose.data.SessionInfo
import com.example.composeapplication.compose.data.fake
import com.example.composeapplication.compose.data.fake3
import com.example.composeapplication.compose.data.fake4
import com.example.composeapplication.compose.data.favorites
import com.example.composeapplication.ui.theme.Red30TechTheme

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    uiState: ConferenceDataUiState,
    onSessionClick: (sessionId: Int) -> Unit = {},
    onFavoriteClick: (sessionId: Int) -> Unit = {},
){
    Column(modifier = modifier.fillMaxSize()){

        when{
            uiState.isLoading -> LoadingIndicator()
            uiState.favorites.isEmpty() -> EmptyFavorites()
            else -> FavoriteLis(
                uiState = uiState,
                onSessionClick = onSessionClick,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Composable
private fun FavoriteLis(
    modifier: Modifier = Modifier,
    uiState: ConferenceDataUiState,
    onSessionClick: (sessionId: Int) -> Unit = {},
    onFavoriteClick: (sessionId: Int) -> Unit = {},
){
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Adaptive(330.dp),
    ) {
        items(uiState.favorites){
            SessionItem(
                sessionInfo = it,
                onSessionClick = onSessionClick,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}


@Composable
fun EmptyFavorites(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(80.dp),
            imageVector = Icons.Filled.BookmarkAdd,
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            contentDescription = null
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.favorites_here),
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview(){
    Red30TechTheme {
        FavoritesScreen(
            uiState = ConferenceDataUiState(
                sessionInfos = listOf(
                    SessionInfo.fake().copy(isFavorite = true),
                    SessionInfo.fake3().copy(isFavorite = true),
                    SessionInfo.fake4().copy(isFavorite = true)
                )
            )
        )
    }
}

@Preview
@Composable
private fun FavoriteScreenLoadingPreview() {
    Red30TechTheme {
        Surface {
            FavoritesScreen(
                uiState = ConferenceDataUiState(isLoading = true),
            )
        }
    }
}

@Preview
@Composable
private fun FavoriteScreenEmptyDataPreview() {
    Red30TechTheme {
        Surface {
            FavoritesScreen(
                uiState = ConferenceDataUiState(
                    isLoading = false,
                )
            )
        }
    }
}