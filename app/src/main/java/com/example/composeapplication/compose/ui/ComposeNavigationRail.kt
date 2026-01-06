package com.example.composeapplication.compose.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composeapplication.compose.ui.screen.topLevelScreens
import com.example.composeapplication.ui.theme.Red30TechTheme

@Composable
fun ComposeNavigationRail(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    currentDestination: NavDestination? = null
) {
    NavigationRail(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.displayCutout)
    ) {
        Spacer(Modifier.weight(1f))

        topLevelScreens.forEach{ screen ->
            val label = stringResource(screen.labelResourceId)
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = label
                    )
                },
                label = {
                    Text(label)
                },
                selected = currentDestination?.hierarchy?.any{
                    it.route == screen.route
                } == true,
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }

    }
}

@Preview
@Composable
private fun NavigationRailPreview() {
    Red30TechTheme {
        Surface {
            ComposeNavigationRail()
        }
    }
}