package com.example.composeapplication.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.compose.ui.component.SessionItem
import com.example.composeapplication.compose.data.ConferenceDataUiState
import com.example.composeapplication.compose.data.fakes
import com.example.composeapplication.compose.data.speakers
import com.example.composeapplication.ui.theme.Red30TechTheme

class LazyLayoutSample {
    @Composable
    fun MainApp(modifier: Modifier = Modifier){
        val uiState = ConferenceDataUiState.fakes()
        Scaffold { innerPadding ->
            /*LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                *//*item {
                    SessionItem(
                        sessionInfo = uiState.sessionInfos.first()
                    )
                }

                items(count = 5){
                    SessionItem(
                        sessionInfo = uiState.sessionInfos.first()
                    )
                }*//*
                items(uiState.sessionInfos){
                    SessionItem(
                        sessionInfo = it
                    )
                }
            }*/

            LazyHorizontalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                rows = GridCells.Fixed(1),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(uiState.sessionInfos.size){
                    Box(modifier = Modifier.width(360.dp)){
                        SessionItem(
                            modifier = Modifier.fillMaxWidth(),
                            sessionInfo = uiState.sessionInfos[it],
                            onSessionClick = {},
                            onFavoriteClick = {}
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LazyLayoutSamplePreview() {
    Red30TechTheme {
        LazyLayoutSample().MainApp()
    }
}