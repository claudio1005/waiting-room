package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface IdeaDao {
    @Query("SELECT * FROM ideas WHERE isCompleted = 0 ORDER BY CASE WHEN postponedTimestamp IS NOT NULL THEN 1 ELSE 0 END ASC, CASE WHEN postponedTimestamp IS NOT NULL THEN postponedTimestamp END ASC, timestamp DESC")
    fun getAllIdeas(): Flow<List<Idea>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdea(idea: Idea)

    @Query("SELECT COUNT(*) FROM ideas WHERE isCompleted = 0")
    fun getIdeasCount(): Flow<Int>

    @Query("UPDATE ideas SET isCompleted = 1, completedTimestamp = :completedTimestamp WHERE id = :id")
    suspend fun markAsCompleted(id: Int, completedTimestamp: Long)

    @Query("SELECT i.id, i.text, i.timestamp, i.isCompleted, i.postponedTimestamp, i.completedTimestamp, (SELECT COUNT(*) FROM notes n WHERE n.ideaId = i.id) as noteCount FROM ideas i WHERE i.isCompleted = 1 ORDER BY i.completedTimestamp DESC")
    fun getArchivedIdeas(): Flow<List<ArchivedIdeaWithNoteCount>>

    @Query("UPDATE ideas SET postponedTimestamp = :postponedTimestamp WHERE id = :id")
    suspend fun postponeIdea(id: Int, postponedTimestamp: Long)

    @Query("DELETE FROM ideas WHERE id = :id")
    suspend fun deleteIdeaById(id: Int)
}
