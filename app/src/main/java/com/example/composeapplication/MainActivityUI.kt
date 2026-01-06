package com.example.composeapplication

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.compose.ComposeActivity
import com.example.composeapplication.flow.FLowUseCaseActivity
import com.example.composeapplication.sample.SampleActivity
import com.example.composeapplication.ui.theme.Red30TechTheme

private data class NavItem(
    val title: String,
    val activity: ComponentActivity,
    val extra: Int? = null
)

private val naveItemList = listOf(
    NavItem("Compose Samples", SampleActivity()),
    NavItem("Compose UI Components", ComposeActivity()),
    NavItem("Flow UseCase 1", FLowUseCaseActivity(), 1),
    NavItem("Flow UseCase 2", FLowUseCaseActivity(), 2),
    NavItem("Flow UseCase 3", FLowUseCaseActivity(), 3)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityUI(modifier: Modifier = Modifier) {
    Red30TechTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Main Activity") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { innerPadding ->
            ScreenUI(modifier = modifier
                    .padding(innerPadding)
            )
        }
    }
}

@Composable
private fun ScreenUI(
    modifier: Modifier = Modifier
){
    val context = LocalContext.current

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Adaptive(330.dp)
    ) {
        items(naveItemList){ naveItem ->
            ListItem(
                title = naveItem.title,
                onClick = {
                    startActivity(
                        context,
                        naveItem.activity,
                        naveItem.extra
                    )
                }
            )
        }
    }

}

private fun startActivity(
    context: Context,
    activity: ComponentActivity,
    extra: Int? = null){
    val intent = Intent(context, activity::class.java)
    extra?.let {
        intent.putExtra("USE_CASE", it)
    }
    context.startActivity(intent)
}


@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    title: String = "Compose UI Component",
    onClick: () -> Unit = {}
){
    ElevatedCard(
        modifier = modifier
            .padding(16.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(0.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListItemPreview(){
    Red30TechTheme {
        ListItem()
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview(){
    Red30TechTheme {
        ScreenUI()
    }
}
@Preview(showBackground = true)
@Composable
fun MainActivityUIPreview(){
    Red30TechTheme {
        MainActivityUI()
    }
}