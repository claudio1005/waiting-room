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
import androidx.compose.material3.Text
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
 * A beautiful, minimal vector illustration of a quiet waiting room.
 * Suggests a peaceful space with a floor line, a sage-green door,
 * a small wooden mid-century modern table, and a potted leafy plant.
 */
@Composable
fun WaitingRoomIllustration(
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    
    // Aesthetic quiet-room palette
    val wallLineColor = if (isDark) Color(0xFF384039) else Color(0xFFDFD8CD)
    val doorColor = if (isDark) Color(0xFF6B7E6E) else Color(0xFF4F6652)
    val doorKnobColor = if (isDark) Color(0xFFD4AF37) else Color(0xFFC5A059)
    val tableColor = if (isDark) Color(0xFF6D5A50) else Color(0xFF8A7060)
    val leafColor = if (isDark) Color(0xFFA5BFA9) else Color(0xFF6B7E6E)
    val potColor = if (isDark) Color(0xFF4E3D35) else Color(0xFFB09586)

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
    ) {
        val width = size.width
        val height = size.height
        
        // Floor baseline
        val floorY = height * 0.85f
        drawLine(
            color = wallLineColor,
            start = androidx.compose.ui.geometry.Offset(0f, floorY),
            end = androidx.compose.ui.geometry.Offset(width, floorY),
            strokeWidth = 2.dp.toPx()
        )

        // Stylized green door (sage accents)
        val doorWidth = 44.dp.toPx()
        val doorHeight = 94.dp.toPx()
        val doorX = width * 0.28f
        val doorY = floorY - doorHeight
        
        drawRoundRect(
            color = doorColor,
            topLeft = androidx.compose.ui.geometry.Offset(doorX, doorY),
            size = androidx.compose.ui.geometry.Size(doorWidth, doorHeight),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(6.dp.toPx(), 6.dp.toPx())
        )
        
        // Door frame highlight
        drawRoundRect(
            color = wallLineColor,
            topLeft = androidx.compose.ui.geometry.Offset(doorX, doorY),
            size = androidx.compose.ui.geometry.Size(doorWidth, doorHeight),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(6.dp.toPx(), 6.dp.toPx()),
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.dp.toPx())
        )

        // Delicate gold door knob
        drawCircle(
            color = doorKnobColor,
            radius = 3.dp.toPx(),
            center = androidx.compose.ui.geometry.Offset(doorX + doorWidth - 8.dp.toPx(), doorY + doorHeight * 0.55f)
        )

        // Minimalist wooden table
        val tableWidth = 54.dp.toPx()
        val tableHeight = 4.dp.toPx()
        val tableX = width * 0.56f
        val tableY = floorY - 34.dp.toPx()
        
        // Angled wooden table legs
        drawLine(
            color = tableColor,
            start = androidx.compose.ui.geometry.Offset(tableX + 8.dp.toPx(), tableY + tableHeight),
            end = androidx.compose.ui.geometry.Offset(tableX + 4.dp.toPx(), floorY),
            strokeWidth = 2.dp.toPx()
        )
        drawLine(
            color = tableColor,
            start = androidx.compose.ui.geometry.Offset(tableX + tableWidth - 8.dp.toPx(), tableY + tableHeight),
            end = androidx.compose.ui.geometry.Offset(tableX + tableWidth - 4.dp.toPx(), floorY),
            strokeWidth = 2.dp.toPx()
        )
        
        // Elegant tabletop
        drawRoundRect(
            color = tableColor,
            topLeft = androidx.compose.ui.geometry.Offset(tableX, tableY),
            size = androidx.compose.ui.geometry.Size(tableWidth, tableHeight),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx(), 2.dp.toPx())
        )

        // Small clay ceramic pot
        val potWidth = 14.dp.toPx()
        val potHeight = 12.dp.toPx()
        val potX = tableX + (tableWidth - potWidth) / 2f
        val potY = tableY - potHeight
        
        val potPath = androidx.compose.ui.graphics.Path().apply {
            moveTo(potX, potY)
            lineTo(potX + potWidth, potY)
            lineTo(potX + potWidth - 2.dp.toPx(), potY + potHeight)
            lineTo(potX + 2.dp.toPx(), potY + potHeight)
            close()
        }
        drawPath(
            path = potPath,
            color = potColor
        )

        // Sage-green plant leaves curving gracefully
        val plantBaseX = potX + potWidth / 2f
        val plantBaseY = potY
        
        // Leaf 1
        val leaf1Path = androidx.compose.ui.graphics.Path().apply {
            moveTo(plantBaseX, plantBaseY)
            quadraticTo(
                plantBaseX - 12.dp.toPx(), plantBaseY - 10.dp.toPx(),
                plantBaseX - 16.dp.toPx(), plantBaseY - 8.dp.toPx()
            )
            quadraticTo(
                plantBaseX - 10.dp.toPx(), plantBaseY - 4.dp.toPx(),
                plantBaseX, plantBaseY
            )
        }
        drawPath(path = leaf1Path, color = leafColor)

        // Leaf 2
        val leaf2Path = androidx.compose.ui.graphics.Path().apply {
            moveTo(plantBaseX, plantBaseY)
            quadraticTo(
                plantBaseX + 14.dp.toPx(), plantBaseY - 14.dp.toPx(),
                plantBaseX + 20.dp.toPx(), plantBaseY - 10.dp.toPx()
            )
            quadraticTo(
                plantBaseX + 12.dp.toPx(), plantBaseY - 2.dp.toPx(),
                plantBaseX, plantBaseY
            )
        }
        drawPath(path = leaf2Path, color = leafColor)

        // Leaf 3
        val leaf3Path = androidx.compose.ui.graphics.Path().apply {
            moveTo(plantBaseX, plantBaseY)
            quadraticTo(
                plantBaseX - 4.dp.toPx(), plantBaseY - 18.dp.toPx(),
                plantBaseX - 2.dp.toPx(), plantBaseY - 22.dp.toPx()
            )
            quadraticTo(
                plantBaseX + 4.dp.toPx(), plantBaseY - 12.dp.toPx(),
                plantBaseX, plantBaseY
            )
        }
        drawPath(path = leaf3Path, color = leafColor)
    }
}

/**
 * The Reception Home Screen, redesigned for a calm, quiet, warm, and highly spacious experience.
 * Inspired by modern minimal interior spaces, it leverages a warm cream background,
 * generous breathing room, sage accents, and a clean stylized room illustration.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaitingRoomScreen(
    viewModel: WaitingRoomViewModel,
    onNavigateToWaitingRoomList: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Collect persistent ideas count reactively from Room DB flow
    val ideasCount by viewModel.ideasCount.collectAsStateWithLifecycle()
    
    // Local input field state
    var ideaText by remember { mutableStateOf("") }
    
    // Save button enabled only with non-blank text
    val isButtonEnabled = ideaText.trim().isNotEmpty()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Upper Section: Branding, Quiet Room Illustration, and Core Input Controls
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Calm Hero Illustration area inspired by the quiet waiting room concept
                WaitingRoomIllustration(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(vertical = 12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Beautiful, generous display title
                Text(
                    text = "Waiting Room",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Light,
                        fontSize = 38.sp,
                        letterSpacing = (-0.5).sp
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Minimalist poetic subtitle
                Text(
                    text = "Le idee possono aspettare.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontStyle = FontStyle.Italic,
                        letterSpacing = 0.5.sp
                    ),
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Multiline Input Text Field with clean rounded corners
                OutlinedTextField(
                    value = ideaText,
                    onValueChange = { ideaText = it },
                    placeholder = {
                        Text(
                            text = "Cosa ti è venuto in mente?",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .testTag("idea_input"),
                    shape = RoundedCornerShape(20.dp),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.35f),
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    ),
                    minLines = 4,
                    maxLines = 6
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Primary Save Button
                Button(
                    onClick = {
                        viewModel.saveIdea(ideaText) {
                            ideaText = "" // Clean input field
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
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.18f),
                        disabledContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 1.dp,
                        pressedElevation = 3.dp
                    )
                ) {
                    Text(
                        text = "Conserva",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            letterSpacing = 1.2.sp
                        )
                    )
                }
            }

            // Bottom Section: Dynamic Counter Card linking to Waiting Room List Screen
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
            ) {
                val counterText = if (ideasCount == 1) {
                    "1 idea in attesa"
                } else {
                    "$ideasCount idee in attesa"
                }

                Card(
                    onClick = onNavigateToWaitingRoomList,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .testTag("ideas_counter_card"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.5.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 18.dp, horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = counterText,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
