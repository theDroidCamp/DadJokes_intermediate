package com.noelon.dadjokes_intermediate.database

import androidx.room.*

@Dao
interface JokeDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJokeDatabaseClass(data: JokeDatabaseClass)

    @Update
    fun editJokeDatabaseClass(updatedJokeDatabaseClass: JokeDatabaseClass)

    @Query("SELECT * FROM jokeDatabaseClass LIMIT 10")
    fun getAllLocalJokesDatabaseClass(): List<JokeDatabaseClass>?
}