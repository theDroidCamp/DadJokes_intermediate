package com.noelon.dadjokes_intermediate.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.noelon.dadjokes_intermediate.network.jokes.Joke

@Entity(tableName = "jokeDatabaseClass")
data class JokeDatabaseClass(
        @PrimaryKey(autoGenerate = false)
        val joke_id: Int?,

        @ColumnInfo
        var isLiked: Boolean = false,

        @ColumnInfo
        val joke: Joke?
)
