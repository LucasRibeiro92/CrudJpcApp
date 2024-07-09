package com.scout.crudjpcapp.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class CJADatabase: RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile private var instance: CJADatabase? = null

        fun getInstance(context: Context): CJADatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CJADatabase::class.java, "cja_database")
                .fallbackToDestructiveMigration()
                .build()
    }
}