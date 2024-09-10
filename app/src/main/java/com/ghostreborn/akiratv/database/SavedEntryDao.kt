package com.ghostreborn.akiratv.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SavedEntryDao {
    @Query("SELECT * FROM saved_entries")
    fun getAll(): List<SavedEntry>

    @Query("SELECT * FROM saved_entries WHERE kitsuID = :id")
    fun getById(id: String): SavedEntry?

    @Query("SELECT * FROM saved_entries WHERE status ='current'")
    fun getCurrent(): List<SavedEntry>

    @Query("SELECT * FROM saved_entries WHERE status ='completed'")
    fun getCompleted(): List<SavedEntry>

    @Query("DELETE FROM saved_entries WHERE kitsuID = :id")
    fun deleteById(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entry: SavedEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg entries: SavedEntry)

}