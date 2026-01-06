package com.example.composeapplication

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToString
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composeapplication.compose.MyCompose
import com.example.composeapplication.compose.ui.screen.SessionsScreen
import com.example.composeapplication.compose.data.ConferenceDataUiState
import com.example.composeapplication.compose.data.fakes
import com.example.composeapplication.ui.theme.Red30TechTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyComposeTexts {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun app_launches(){
        composeTestRule.setContent {
            MyCompose()
        }

        composeTestRule.apply {
            println(onRoot().printToString())
        }
    }

    @Test
    fun loading_indicator_properly_displayed(){
        composeTestRule.setContent {
            Red30TechTheme {
                SessionsScreen(
                    uiState = ConferenceDataUiState(isLoading = true),
                )
            }
        }
        composeTestRule
            .onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate))
            .assertIsDisplayed()
    }

    @Test
    fun should_display_empty_view_when_no_data(){
        composeTestRule.setContent {
            Red30TechTheme {
                SessionsScreen(
                    uiState = ConferenceDataUiState(isLoading = false)
                )
            }
        }

        composeTestRule.onNodeWithTag("ui:emptyConferenceData").assertIsDisplayed()

    }
    @Test
    fun should_display_day2_chip_as_selected(){
        composeTestRule.setContent {
            Red30TechTheme {
                SessionsScreen(
                    uiState = ConferenceDataUiState.fakes()
                )
            }
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.day_2_label))
            .performClick()
            .assertIsDisplayed()

    }

    @Test
    fun should_allow_session_to_be_favorited(){
        composeTestRule.setContent {
           MyCompose()
        }
        composeTestRule.apply {
            onAllNodes(hasTestTag("ui:sessionItem"))
                .onFirst()
                .onChildren()
                .filterToOne(hasClickAction())
                .performClick()
                .assert(isOn() and hasContentDescription("un-favorite session"))
        }

    }

}