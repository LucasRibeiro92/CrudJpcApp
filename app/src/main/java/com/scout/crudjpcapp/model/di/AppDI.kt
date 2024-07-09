package com.scout.crudjpcapp.model.di

import android.content.Context
import androidx.room.Room
import com.scout.crudjpcapp.model.data.CJADatabase

fun provideDatabase(context: Context): CJADatabase =
    Room.databaseBuilder(context, CJADatabase::class.java, "cja_database")
        .fallbackToDestructiveMigration()
        .build()

fun provideContactDao(db: CJADatabase) = db.contactDao()