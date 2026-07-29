package com.example.data

data class ArchivedIdeaWithNoteCount(
    val id: Int,
    val text: String,
    val timestamp: Long,
    val isCompleted: Boolean,
    val postponedTimestamp: Long?,
    val completedTimestamp: Long?,
    val noteCount: Int
)
