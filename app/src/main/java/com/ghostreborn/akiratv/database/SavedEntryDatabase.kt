package com.ghostreborn.akiratv.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SavedEntry::class], version = 1, exportSchema = false)
abstract class SavedEntryDatabase: RoomDatabase() {
    abstract fun savedEntryDao(): SavedEntryDao
}