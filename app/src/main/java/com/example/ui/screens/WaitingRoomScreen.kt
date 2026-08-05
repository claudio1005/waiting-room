package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.example.ui.theme.ReceptionBackground
import com.example.ui.theme.ReceptionGreen
import com.example.ui.theme.WarmSand
import com.example.ui.theme.MutedBeige
import com.example.ui.theme.SageText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.viewmodel.WaitingRoomViewModel
import kotlinx.coroutines.launch

/**
 * A beautiful, detailed vector illustration of a quiet reception area.
 * Matches the mockup with a hanging lamp, botanical print, console table,
 * potted plants, and an olive-green door.
 */
@Composable
fun ReceptionRoomIllustration(
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    
    // Palette based on mockup
    val floorColor = if (isDark) Color(0xFF384039) else MutedBeige
    val doorColor = if (isDark) Color(0xFF8B9D8F) else ReceptionGreen
    val woodColor = if (isDark) Color(0xFF6D5A50) else WarmSand
    val leafColor = if (isDark) Color(0xFFA5BFA9) else SageText
    val lampColor = if (isDark) Color(0xFFE2E4E2) else WarmSand
    val frameColor = if (isDark) Color(0xFF4E3D35) else WarmSand

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
    ) {
        val width = size.width
        val height = size.height
        
        // Floor baseline
        val floorY = height * 0.95f
        drawLine(
            color = floorColor,
            start = androidx.compose.ui.geometry.Offset(0f, floorY),
            end = androidx.compose.ui.geometry.Offset(width, floorY),
            strokeWidth = 2.dp.toPx()
        )

        // --- Door (Right side) ---
        val doorWidth = 60.dp.toPx()
        val doorHeight = 160.dp.toPx()
        val doorX = width * 0.7f
        val doorY = floorY - doorHeight
        
        // Door body
        drawRoundRect(
            color = doorColor,
            topLeft = androidx.compose.ui.geometry.Offset(doorX, doorY),
            size = androidx.compose.ui.geometry.Size(doorWidth, doorHeight),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx(), 4.dp.toPx())
        )
        
        // Door knob
        drawCircle(
            color = Color(0xFFC5A059),
            radius = 3.dp.toPx(),
            center = androidx.compose.ui.geometry.Offset(doorX + 12.dp.toPx(), doorY + doorHeight * 0.55f)
        )

        // --- Console Table (Left side) ---
        val tableWidth = 80.dp.toPx()
        val tableTopHeight = 6.dp.toPx()
        val tableX = width * 0.15f
        val tableY = floorY - 60.dp.toPx()
        
        // Table Legs
        drawLine(
            color = woodColor,
            start = androidx.compose.ui.geometry.Offset(tableX + 10.dp.toPx(), tableY),
            end = androidx.compose.ui.geometry.Offset(tableX + 5.dp.toPx(), floorY),
            strokeWidth = 3.dp.toPx()
        )
        drawLine(
            color = woodColor,
            start = androidx.compose.ui.geometry.Offset(tableX + tableWidth - 10.dp.toPx(), tableY),
            end = androidx.compose.ui.geometry.Offset(tableX + tableWidth - 5.dp.toPx(), floorY),
            strokeWidth = 3.dp.toPx()
        )
        
        // Table Top
        drawRoundRect(
            color = woodColor,
            topLeft = androidx.compose.ui.geometry.Offset(tableX, tableY),
            size = androidx.compose.ui.geometry.Size(tableWidth, tableTopHeight),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx(), 2.dp.toPx())
        )

        // --- Plant on Table ---
        val potX = tableX + tableWidth * 0.3f
        val potWidth = 12.dp.toPx()
        val potHeight = 10.dp.toPx()
        val potY = tableY - potHeight
        
        drawRect(
            color = woodColor,
            topLeft = androidx.compose.ui.geometry.Offset(potX, potY),
            size = androidx.compose.ui.geometry.Size(potWidth, potHeight)
        )
        
        // Leaves
        drawCircle(color = leafColor, radius = 6.dp.toPx(), center = androidx.compose.ui.geometry.Offset(potX + 4.dp.toPx(), potY - 4.dp.toPx()))
        drawCircle(color = leafColor, radius = 5.dp.toPx(), center = androidx.compose.ui.geometry.Offset(potX + potWidth - 2.dp.toPx(), potY - 6.dp.toPx()))

        // --- Framed Botanical Print ---
        val frameWidth = 40.dp.toPx()
        val frameHeight = 55.dp.toPx()
        val frameX = tableX + (tableWidth - frameWidth) / 2
        val frameY = tableY - 110.dp.toPx()
        
        drawRect(
            color = frameColor,
            topLeft = androidx.compose.ui.geometry.Offset(frameX, frameY),
            size = androidx.compose.ui.geometry.Size(frameWidth, frameHeight),
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx())
        )
        
        // Simple leaf inside frame
        drawCircle(
            color = leafColor.copy(alpha = 0.6f),
            radius = 8.dp.toPx(),
            center = androidx.compose.ui.geometry.Offset(frameX + frameWidth/2, frameY + frameHeight/2)
        )

        // --- Hanging Lamp ---
        val lampX = frameX + frameWidth / 2
        val lampDomeRadius = 15.dp.toPx()
        val lampY = frameY - 60.dp.toPx()
        
        // Lamp wire
        drawLine(
            color = frameColor,
            start = androidx.compose.ui.geometry.Offset(lampX, 0f),
            end = androidx.compose.ui.geometry.Offset(lampX, lampY),
            strokeWidth = 1.dp.toPx()
        )
        
        // Lamp dome
        drawArc(
            color = lampColor,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = androidx.compose.ui.geometry.Offset(lampX - lampDomeRadius, lampY),
            size = androidx.compose.ui.geometry.Size(lampDomeRadius * 2, lampDomeRadius * 2)
        )

        // --- Plant on Floor (Right) ---
        val floorPlantX = width * 0.88f
        val floorPotWidth = 16.dp.toPx()
        val floorPotHeight = 14.dp.toPx()
        val floorPotY = floorY - floorPotHeight
        
        drawRect(
            color = woodColor,
            topLeft = androidx.compose.ui.geometry.Offset(floorPlantX, floorPotY),
            size = androidx.compose.ui.geometry.Size(floorPotWidth, floorPotHeight)
        )
        
        // Tall leaves
        drawLine(color = leafColor, start = androidx.compose.ui.geometry.Offset(floorPlantX + 8.dp.toPx(), floorPotY), end = androidx.compose.ui.geometry.Offset(floorPlantX + 4.dp.toPx(), floorPotY - 30.dp.toPx()), strokeWidth = 4.dp.toPx())
        drawLine(color = leafColor, start = androidx.compose.ui.geometry.Offset(floorPlantX + 8.dp.toPx(), floorPotY), end = androidx.compose.ui.geometry.Offset(floorPlantX + 14.dp.toPx(), floorPotY - 25.dp.toPx()), strokeWidth = 3.dp.toPx())
    }
}

/**
 * The Reception Home Screen, redesigned to match the mockup.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaitingRoomScreen(
    viewModel: WaitingRoomViewModel,
    onNavigateToWaitingRoomList: () -> Unit,
    modifier: Modifier = Modifier
) {
    val ideasCount by viewModel.ideasCount.collectAsStateWithLifecycle()
    var ideaText by remember { mutableStateOf("") }
    val isButtonEnabled = ideaText.trim().isNotEmpty()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = ReceptionBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Detailed room illustration
            ReceptionRoomIllustration(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            // Centered Title
            Text(
                text = "Waiting Room",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 32.sp,
                    letterSpacing = (-0.5).sp
                ),
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            // Centered Subtitle
            Text(
                text = "Le idee possono aspettare.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontStyle = FontStyle.Italic,
                    color = SageText
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
            )

            // Input Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                shadowElevation = 2.dp
            ) {
                Box(modifier = Modifier.padding(20.dp)) {
                    androidx.compose.foundation.text.BasicTextField(
                        value = ideaText,
                        onValueChange = { ideaText = it },
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag("idea_input"),
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                        decorationBox = { innerTextField ->
                            if (ideaText.isEmpty()) {
                                Text(
                                    text = "Cosa ti è venuto in mente?",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.Gray.copy(alpha = 0.6f)
                                )
                            }
                            innerTextField()
                        }
                    )
                    
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = "Dettatura vocale",
                        tint = Color.Black.copy(alpha = 0.4f),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Conserva Button
            Button(
                onClick = {
                    viewModel.saveIdea(ideaText) {
                        ideaText = ""
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("La tua idea è al sicuro.")
                        }
                    }
                },
                enabled = isButtonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("conserva_button"),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ReceptionGreen,
                    contentColor = Color.White,
                    disabledContainerColor = ReceptionGreen.copy(alpha = 0.3f),
                    disabledContentColor = Color.White.copy(alpha = 0.6f)
                )
            ) {
                Text(
                    text = "Conserva",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Bottom Navigation Card
            Card(
                onClick = onNavigateToWaitingRoomList,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ideas_counter_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = Color.Black.copy(alpha = 0.7f),
                        modifier = Modifier.size(28.dp)
                    )
                    
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Waiting Room",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                        val counterText = if (ideasCount == 1) "1 idea in attesa" else "$ideasCount idee in attesa"
                        Text(
                            text = counterText,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                    
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}
