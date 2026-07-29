package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.Idea
import com.example.data.IdeaRepository
import com.example.data.Note
import com.example.data.ArchivedIdeaWithNoteCount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WaitingRoomViewModel(private val repository: IdeaRepository) : ViewModel() {

    /**
     * Gets a Flow of notes for a specific idea.
     */
    fun getNotesForIdea(ideaId: Int): Flow<List<Note>> {
        return repository.getNotesForIdea(ideaId)
    }

    /**
     * Saves a new note persistently. Invokes [onCompleted] on success.
     */
    fun saveNote(ideaId: Int, text: String, onCompleted: () -> Unit = {}) {
        val trimmed = text.trim()
        if (trimmed.isNotEmpty()) {
            viewModelScope.launch {
                repository.insertNote(Note(ideaId = ideaId, text = trimmed))
                onCompleted()
            }
        }
    }

    fun markAsCompleted(ideaId: Int) {
        viewModelScope.launch {
            repository.markAsCompleted(ideaId, System.currentTimeMillis())
        }
    }

    fun postponeIdea(ideaId: Int, postponedTimestamp: Long) {
        viewModelScope.launch {
            repository.postponeIdea(ideaId, postponedTimestamp)
        }
    }

    fun deleteIdeaAndNotes(ideaId: Int) {
        viewModelScope.launch {
            repository.deleteIdeaAndNotes(ideaId)
        }
    }

    // Reactive StateFlow for ideas count
    val ideasCount: StateFlow<Int> = repository.ideasCount
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    // Reactive StateFlow for all saved ideas (sorted newest first by database query)
    val allIdeas: StateFlow<List<Idea>> = repository.allIdeas
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Reactive StateFlow for archived completed ideas
    val archivedIdeas: StateFlow<List<ArchivedIdeaWithNoteCount>> = repository.archivedIdeas
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    /**
     * Saves a new idea persistently. Invokes [onCompleted] on success.
     */
    fun saveIdea(text: String, onCompleted: () -> Unit) {
        val trimmed = text.trim()
        if (trimmed.isNotEmpty()) {
            viewModelScope.launch {
                repository.insert(Idea(text = trimmed))
                onCompleted()
            }
        }
    }
}

class WaitingRoomViewModelFactory(private val repository: IdeaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WaitingRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WaitingRoomViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
