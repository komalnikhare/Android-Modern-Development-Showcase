package com.example.composeapplication.compose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.composeapplication.compose.ui.component.EmptyConferenceData
import com.example.composeapplication.compose.ui.component.SpeakerImage
import com.example.composeapplication.compose.data.ConferenceDataUiState
import com.example.composeapplication.compose.data.SessionInfo
import com.example.composeapplication.compose.data.Speaker
import com.example.composeapplication.compose.data.fake
import com.example.composeapplication.compose.data.fake3
import com.example.composeapplication.compose.data.fake4
import com.example.composeapplication.compose.data.fake5
import com.example.composeapplication.compose.data.fake6
import com.example.composeapplication.compose.data.speakers
import com.example.composeapplication.ui.theme.Red30TechTheme

@Composable
fun SpeakerScreen(
    modifier: Modifier = Modifier,
    uiState: ConferenceDataUiState
) {
    if (!uiState.isLoading && uiState.speakers.isEmpty()){
        EmptyConferenceData(modifier = modifier)
    }else {
        SpeakerList(
            modifier = modifier,
            speakers = uiState.speakers
        )
    }
}

@Composable
private fun SpeakerList(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
    speakers: List<Speaker>
){

    BoxWithConstraints {
        val maxWidth = this@BoxWithConstraints.maxWidth
        val desiredItemSize = 400.dp
        val width = maxWidth / (maxWidth/desiredItemSize)
        val isNotExpandedWidth = windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.EXPANDED

        val columns = when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> StaggeredGridCells.Fixed(1)
            WindowWidthSizeClass.MEDIUM -> StaggeredGridCells.Fixed(2)
            else -> StaggeredGridCells.Adaptive(width)
        }

        LazyVerticalStaggeredGrid (
            modifier = modifier.fillMaxSize(),
            columns = columns
        ) {
            items(speakers) {
                if (width <= desiredItemSize && isNotExpandedWidth)
                    PortraitSpeakerItem(speaker = it)
                else
                    SpeakerItem(speaker = it)
            }
        }
    }

}

@Composable
fun PortraitSpeakerItem(
    modifier: Modifier = Modifier,
    speaker: Speaker
) {
    ElevatedCard(
        modifier = modifier
            .padding(16.dp)
            .testTag("ui:portrait-speakerItem"),
        shape = RoundedCornerShape(0.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpeakerImage(
                speaker = speaker,
                imageSize = 88.dp
            )
            SpeakerDetails(
                modifier = Modifier.fillMaxWidth(),
                shouldCenter = true,
                speaker = speaker
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = speaker.bio,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@Composable
fun SpeakerItem(
    modifier: Modifier = Modifier,
    speaker: Speaker
) {
    ElevatedCard (
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(0.dp),
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SpeakerImage(speaker = speaker)
                SpeakerDetails(speaker = speaker)
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = speaker.bio,
            )
        }
    }
}

@Composable
fun SpeakerDetails(
    modifier: Modifier = Modifier,
    shouldCenter: Boolean = false,
    speaker: Speaker
) {
    Column(
        modifier = modifier,
        horizontalAlignment = if (shouldCenter)
            Alignment.CenterHorizontally
        else
            Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            style = LocalTextStyle.current.merge(
                MaterialTheme.typography.headlineSmall
            ),
            text = speaker.name,
        )
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            fontWeight = FontWeight.Bold,
            text = speaker.title,
        )
        Text(
            fontStyle = FontStyle.Italic,
            text = speaker.organization,
        )
    }
}


@PreviewScreenSizes
@Composable
private fun SessionScreenPreview() {
    Red30TechTheme {
        SpeakerScreen(
            uiState = ConferenceDataUiState(
                sessionInfos = listOf(
                    SessionInfo.fake(),
                    SessionInfo.fake3(),
                    SessionInfo.fake4(),
                    SessionInfo.fake5(),
                    SessionInfo.fake6(),
                )
            )
        )
    }
}