package com.example.composeapplication.flow.usecase2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeapplication.R
import com.example.composeapplication.compose.ui.component.EmptyConferenceData
import com.example.composeapplication.flow.mock.Currency
import com.example.composeapplication.flow.mock.PriceTrend
import com.example.composeapplication.flow.mock.Stock
import com.example.composeapplication.flow.usecase1.UiState
import com.example.composeapplication.ui.theme.Red30TechTheme
import org.koin.androidx.compose.koinViewModel

private const val TEXT_TO_PARENT_SIZE_RATIO = 0.6

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowUseCase2UI(
    modifier: Modifier = Modifier,
    viewModel: FlowUseCase2ViewModel = koinViewModel<FlowUseCase2ViewModel>()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Red30TechTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Flow UseCase2") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        titleContentColor = MaterialTheme.colorScheme.onTertiary
                    )
                )
            }
        ) { innerPadding ->
            StockListScreen(
                modifier = modifier.padding(innerPadding),
                uiState = uiState)
        }


    }

}


@Composable
fun StockListScreen(
    modifier: Modifier = Modifier,
    uiState: UiState
){
    when(uiState) {
        is UiState.Loading -> {
            LoadingIndicator(modifier = modifier)
        }
        is UiState.Error -> {
            EmptyConferenceData(modifier = modifier)
        }
        else -> {
            StockList(
                modifier = modifier,
                stocks = (uiState as UiState.Success).stockList
            )
        }
    }
}


@Composable
fun StockList(
    modifier: Modifier = Modifier,
    stocks: List<Stock>
){
   LazyVerticalGrid(
       modifier = modifier
           .fillMaxSize(),
       columns = GridCells.Adaptive(330.dp)
   ) {
       items(stocks){
           StockItem(stock = it)
       }
   }
}

@Composable
fun StockItem(stock: Stock){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        ) {
        Text(
            text = "${stock.rank}",
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .padding(end = 16.dp)
        )
        Text(
            modifier = Modifier.weight(1f),
            text = stock.name,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "${currencySymbols[stock.currency.name]}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 5.dp)
        )
        Text(
            text = "${stock.currentPrice}",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = if (stock.priceTrend== PriceTrend.UP) Color.Green
            else Color.Red,
        )
    }
}



@Composable
fun LoadingIndicator(modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(64.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(stringResource(R.string.loading))

    }
}

@Preview(showBackground = true)
@Composable
fun StockItemPreview(){
    Red30TechTheme {
        StockItem(stock = getFakeStock)
    }
}

@Preview(showBackground = true)
@Composable
fun StockListScreenPreview(){
    Red30TechTheme {
        StockListScreen(
            uiState = UiState.Success(
                listOf(
                    getFakeStock,
                    getFakeStock,
                    getFakeStock,
                    getFakeStock
                )
            )
        )
    }
}


private val getFakeStock = Stock(
    rank = 1,
    name = "Apple",
    symbol = "$",
    marketCap = 120.4f,
    country = "INR",
    currentPrice = 12000f,
    currency = Currency.DOLLAR,
    priceTrend =  PriceTrend.UP
)

val currencySymbols = mapOf(
    "DOLLAR" to "$",
    "EURO" to "€",
    "POUND" to "£",
    "YEN" to "¥"
)