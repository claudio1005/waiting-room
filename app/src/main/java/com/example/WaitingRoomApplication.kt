package com.example

import android.app.Application
import com.example.data.AppDatabase
import com.example.data.IdeaRepository

class WaitingRoomApplication : Application() {
    // Lazy initialization of database and repository for optimal startup performance
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { IdeaRepository(database.ideaDao(), database.noteDao()) }
}
