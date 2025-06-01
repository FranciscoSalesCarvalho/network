package com.francisco.network

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.francisco.network.presentation.CountriesScreen
import com.francisco.network.presentation.CountriesViewModel
import com.francisco.network.ui.theme.NetworkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state by viewModel.state.collectAsState()
                Scaffold { innerPadding ->
                    CountriesScreen(
                        state = state,
                        onSelectCountry = viewModel::selectCountry,
                        onDismissCountryDialog = viewModel::dismissCountryDialog,
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

