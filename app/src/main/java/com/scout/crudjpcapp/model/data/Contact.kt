package com.scout.crudjpcapp.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: UUID = UUID.randomUUID(),
    var name: String,
    var number: Int
)
