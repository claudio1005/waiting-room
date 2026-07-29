package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.ui.screens.ArchiveScreen
import com.example.ui.screens.WaitingRoomListScreen
import com.example.ui.screens.WaitingRoomScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.WaitingRoomViewModel
import com.example.ui.viewmodel.WaitingRoomViewModelFactory

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    // Retrieve database repository singleton from custom Application class
    val app = application as WaitingRoomApplication
    val factory = WaitingRoomViewModelFactory(app.repository)
    val viewModel: WaitingRoomViewModel by viewModels { factory }

    setContent {
      MyApplicationTheme {
        var currentScreen by remember { mutableStateOf("reception") }

        when (currentScreen) {
          "reception" -> {
            WaitingRoomScreen(
              viewModel = viewModel,
              onNavigateToWaitingRoomList = { currentScreen = "list" },
              modifier = Modifier.fillMaxSize()
            )
          }
          "list" -> {
            // Support Android system back button/gesture to return to Reception screen
            BackHandler {
              currentScreen = "reception"
            }

            WaitingRoomListScreen(
              viewModel = viewModel,
              onBack = { currentScreen = "reception" },
              onNavigateToArchive = { currentScreen = "archive" },
              modifier = Modifier.fillMaxSize()
            )
          }
          "archive" -> {
            // Support Android system back button/gesture to return to WaitingRoomList screen
            BackHandler {
              currentScreen = "list"
            }

            ArchiveScreen(
              viewModel = viewModel,
              onBack = { currentScreen = "list" },
              modifier = Modifier.fillMaxSize()
            )
          }
        }
      }
    }
  }
}

