package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.draw.shadow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.example.data.Idea
import com.example.data.Note
import com.example.ui.viewmodel.WaitingRoomViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Screen displaying the list of all saved ideas arranged in a responsive 2-column grid.
 * Each idea is styled to look like a small paper note pinned on a board with slightly
 * rounded corners and a natural, deterministic, slight rotation (about ±2°).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaitingRoomListScreen(
    viewModel: WaitingRoomViewModel,
    onBack: () -> Unit,
    onNavigateToArchive: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Observe all ideas reactively from the database (sorted newest first)
    val ideas by viewModel.allIdeas.collectAsStateWithLifecycle()

    val dateFormatter = remember { SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN) }
    val timeFormatter = remember { SimpleDateFormat("HH:mm", Locale.ITALIAN) }

    // State to keep track of the selected idea for the Bottom Sheet detail view
    var selectedIdea by remember { mutableStateOf<Idea?>(null) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFFAF6EE), // Warm cream wall background
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Waiting Room",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-0.5).sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Torna alla reception"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = onNavigateToArchive,
                        modifier = Modifier.testTag("archive_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Archive,
                            contentDescription = "Archivio"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFAF6EE), // Transparent/cream bar to blend with wall
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(0.12f)) // Push board down and center it vertically

            // Large elegant cork-board area
            Box(
                modifier = Modifier
                    .weight(0.72f) // Occupies ~72% height instead of full weight
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .shadow(
                        elevation = 12.dp, // Subtle soft wall shadow behind the board
                        shape = RoundedCornerShape(24.dp),
                        clip = false
                    )
                    .background(Color(0xFFC89D7C), shape = RoundedCornerShape(24.dp)) // Warm, rich textured cork color
                    .border(
                        BorderStroke(10.dp, Color(0xFF5D4037)), // Elegant deep-brown wooden frame
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(16.dp)
            ) {
                if (ideas.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "La bacheca è vuota.\nNessuna idea in attesa.",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Medium,
                                lineHeight = 24.sp
                            ),
                            color = Color(0xFF5D4037).copy(alpha = 0.8f),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag("ideas_list"),
                        contentPadding = PaddingValues(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp), // slightly larger spacing between notes
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(ideas, key = { it.id }) { idea ->
                            PostItNoteItem(
                                idea = idea,
                                viewModel = viewModel,
                                dateFormatter = dateFormatter,
                                timeFormatter = timeFormatter,
                                onClick = { selectedIdea = idea }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(0.12f)) // Balanced spacing at bottom for vertical centering

            // Visual-only search field near the bottom
            var searchQuery by remember { mutableStateOf("") }
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .testTag("search_field"),
                placeholder = {
                    Text(
                        text = "Cerca un’idea...",
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Cerca"
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(28.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    cursorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }

    // Modal Bottom Sheet details display
    selectedIdea?.let { idea ->
        val dateStr = remember(idea.timestamp) { dateFormatter.format(Date(idea.timestamp)) }
        val timeStr = remember(idea.timestamp) { timeFormatter.format(Date(idea.timestamp)) }

        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current

        // Local state for the new note text
        var noteInput by remember { mutableStateOf("") }

        // State for delete confirmation dialog
        var showDeleteConfirmation by remember { mutableStateOf(false) }

        // Collect notes for the selected idea reactively
        val notes by remember(idea.id) {
            viewModel.getNotesForIdea(idea.id)
        }.collectAsStateWithLifecycle(initialValue = emptyList())

        ModalBottomSheet(
            onDismissRequest = { selectedIdea = null },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            dragHandle = {
                BottomSheetDefaults.DragHandle(
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.25f)
                )
            },
            modifier = Modifier.fillMaxHeight(0.75f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 24.dp, top = 8.dp)
            ) {
                // The full idea text as the title (large, elegant typography)
                Text(
                    text = idea.text,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 34.sp,
                        letterSpacing = (-0.5).sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Creation date & creation time
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Data: $dateStr",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Text(
                        text = "Ora: $timeStr",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                if (idea.postponedTimestamp != null) {
                    val postponedDateStr = remember(idea.postponedTimestamp) { dateFormatter.format(Date(idea.postponedTimestamp)) }
                    val postponedTimeStr = remember(idea.postponedTimestamp) { timeFormatter.format(Date(idea.postponedTimestamp)) }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Rimandata il: $postponedDateStr",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontStyle = FontStyle.Italic
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = "Ore: $postponedTimeStr",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontStyle = FontStyle.Italic
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Title: “APPUNTI”
                Text(
                    text = "APPUNTI",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Scrollable container for notes so that add-note input and action buttons remain visible at the bottom
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (notes.isEmpty()) {
                        Text(
                            text = "Nessun appunto ancora.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        )
                    } else {
                        notes.forEach { note ->
                            val noteDateStr = remember(note.timestamp) {
                                dateFormatter.format(Date(note.timestamp)) + " " + timeFormatter.format(Date(note.timestamp))
                            }
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.4f),
                                    contentColor = MaterialTheme.colorScheme.onSurface
                                ),
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.08f)
                                ),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = note.text,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            lineHeight = 22.sp
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = noteDateStr,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Add note input row
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = noteInput,
                        onValueChange = { noteInput = it },
                        placeholder = {
                            Text(
                                text = "Aggiungi un appunto...",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("note_input"),
                        shape = RoundedCornerShape(16.dp),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedContainerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
                            unfocusedContainerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.3f)
                        ),
                        maxLines = 4,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (noteInput.trim().isNotEmpty()) {
                                    viewModel.saveNote(idea.id, noteInput) {
                                        noteInput = ""
                                    }
                                }
                                keyboardController?.hide()
                                focusManager.clearFocus()
                            }
                        )
                    )

                    IconButton(
                        onClick = {
                            if (noteInput.trim().isNotEmpty()) {
                                viewModel.saveNote(idea.id, noteInput) {
                                    noteInput = "" // Clear input field after saving
                                }
                            }
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        },
                        enabled = noteInput.trim().isNotEmpty(),
                        modifier = Modifier.testTag("save_note_button")
                    ) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Add,
                            contentDescription = "Salva appunto",
                            tint = if (noteInput.trim().isNotEmpty()) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // FATTO: Sage-green styling (calm, soft, no bright colors)
                    Button(
                        onClick = {
                            viewModel.markAsCompleted(idea.id)
                            selectedIdea = null
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("action_fatto"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE2EFE0), // very soft light sage green
                            contentColor = Color(0xFF2E4D2C) // dark forest green for text
                        ),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(vertical = 14.dp)
                    ) {
                        Text(
                            text = "✓ Fatto",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    // RIMANDA: Soft blue/neutral styling (calm, soft)
                    Button(
                        onClick = {
                            viewModel.postponeIdea(idea.id, System.currentTimeMillis())
                            selectedIdea = null
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("action_rimanda"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE3EDF7), // very soft neutral-blue
                            contentColor = Color(0xFF1F4E79) // deep slate blue for text
                        ),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(vertical = 14.dp)
                    ) {
                        Text(
                            text = "🕒 Rimanda",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    // ELIMINA: Subtle muted red styling
                    Button(
                        onClick = {
                            showDeleteConfirmation = true
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("action_elimina"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFBEBEB), // very soft muted red
                            contentColor = Color(0xFF9E2A2B) // dark red for text
                        ),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(vertical = 14.dp)
                    ) {
                        Text(
                            text = "🗑 Elimina",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        if (showDeleteConfirmation) {
            AlertDialog(
                onDismissRequest = { showDeleteConfirmation = false },
                title = {
                    Text(
                        text = "Eliminare questa idea?",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.deleteIdeaAndNotes(idea.id)
                            showDeleteConfirmation = false
                            selectedIdea = null
                        },
                        modifier = Modifier.testTag("confirm_delete_button")
                    ) {
                        Text(
                            text = "Elimina",
                            color = Color(0xFF9E2A2B),
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDeleteConfirmation = false
                        },
                        modifier = Modifier.testTag("cancel_delete_button")
                    ) {
                        Text(
                            text = "Annulla",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                },
                containerColor = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}

@Composable
fun PostItNoteItem(
    idea: Idea,
    viewModel: WaitingRoomViewModel,
    dateFormatter: SimpleDateFormat,
    timeFormatter: SimpleDateFormat,
    onClick: () -> Unit
) {
    val notes by viewModel.getNotesForIdea(idea.id).collectAsStateWithLifecycle(initialValue = emptyList())
    val notesCount = notes.size

    val dateStr = remember(idea.timestamp) { dateFormatter.format(Date(idea.timestamp)) }

    // Calculate a deterministic rotation between -2.0 and +2.0 degrees based on the unique id
    val rotationDegrees = remember(idea.id) {
        val seed = (idea.id * 73 + 13) % 41
        (seed - 20) / 10f // yields a value from -2.0f to +2.0f
    }

    // Determine pastel color
    val pastelColors = remember {
        listOf(
            Color(0xFFFFF9C4), // Pale yellow
            Color(0xFFE8F5E9), // Sage green
            Color(0xFFE3F2FD), // Dusty blue
            Color(0xFFFCE4EC)  // Soft pink
        )
    }
    val postItBgColor = remember(idea.id) { pastelColors[idea.id % pastelColors.size] }

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                rotationZ = rotationDegrees
            }
            .shadow(
                elevation = 6.dp, // slightly more pronounced soft shadow for depth
                shape = RoundedCornerShape(6.dp),
                ambientColor = Color.Black.copy(alpha = 0.12f),
                spotColor = Color.Black.copy(alpha = 0.22f)
            )
            .testTag("idea_card_${idea.id}"),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = postItBgColor,
            contentColor = Color(0xFF1A1A1A) // Elegant dark charcoal ink text color
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp) // padding to make space for the pin
        ) {
            // A realistic red pushpin drawn at the top center of each post-it
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(12.dp) // slightly larger pin
                    .background(Color(0xFFD32F2F), shape = CircleShape)
                    .border(1.dp, Color(0xFFB71C1C), CircleShape)
                    .shadow(1.dp, CircleShape)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 18.dp) // Increased padding by ~20% for larger post-its
                    .padding(top = 8.dp)
            ) {
                // Prominent idea text with improved size, weight, and contrast for readability
                Text(
                    text = idea.text,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        lineHeight = 22.sp,
                        letterSpacing = (-0.1).sp
                    ),
                    color = Color(0xFF1A1A1A),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp)) // slightly larger vertical spacing

                // Bottom row with date & notes count icon
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = dateStr,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF444444),
                        fontWeight = FontWeight.Normal
                    )

                    // Note count icon + number styled as an elegant, highly-noticeable soft badge
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .background(
                                color = if (notesCount > 0) Color(0xFF2E4D2C).copy(alpha = 0.12f) else Color.Black.copy(alpha = 0.05f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = "Appunti",
                            tint = if (notesCount > 0) Color(0xFF2E4D2C) else Color(0xFF555555),
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = notesCount.toString(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = if (notesCount > 0) Color(0xFF2E4D2C) else Color(0xFF555555)
                        )
                    }
                }
            }
        }
    }
}

