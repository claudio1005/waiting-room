package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ideas")
data class Idea(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false,
    val postponedTimestamp: Long? = null,
    val completedTimestamp: Long? = null
)
