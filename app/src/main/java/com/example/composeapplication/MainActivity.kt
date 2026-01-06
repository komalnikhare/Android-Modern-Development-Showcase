package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapplication.compose.MyCompose
import com.example.composeapplication.compose.MyComposePreview
import com.example.composeapplication.flow.usecase1.FlowUseCase1Ui
import com.example.composeapplication.flow.usecase2.FlowUseCase2UI
import com.example.composeapplication.flow.usecase3.FlowUseCase3UI
import com.example.composeapplication.ui.theme.Red30TechTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainActivityUI()
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Red30TechTheme {
            MainActivityUI()
        }
    }
}
