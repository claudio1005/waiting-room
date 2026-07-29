package com.example.data

import kotlinx.coroutines.flow.Flow

class IdeaRepository(
    private val ideaDao: IdeaDao,
    private val noteDao: NoteDao
) {
    val allIdeas: Flow<List<Idea>> = ideaDao.getAllIdeas()
    val ideasCount: Flow<Int> = ideaDao.getIdeasCount()
    val archivedIdeas: Flow<List<ArchivedIdeaWithNoteCount>> = ideaDao.getArchivedIdeas()

    suspend fun insert(idea: Idea) {
        ideaDao.insertIdea(idea)
    }

    fun getNotesForIdea(ideaId: Int): Flow<List<Note>> {
        return noteDao.getNotesForIdea(ideaId)
    }

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun markAsCompleted(ideaId: Int, completedTimestamp: Long) {
        ideaDao.markAsCompleted(ideaId, completedTimestamp)
    }

    suspend fun postponeIdea(ideaId: Int, postponedTimestamp: Long) {
        ideaDao.postponeIdea(ideaId, postponedTimestamp)
    }

    suspend fun deleteIdeaAndNotes(ideaId: Int) {
        noteDao.deleteNotesForIdea(ideaId)
        ideaDao.deleteIdeaById(ideaId)
    }
}
