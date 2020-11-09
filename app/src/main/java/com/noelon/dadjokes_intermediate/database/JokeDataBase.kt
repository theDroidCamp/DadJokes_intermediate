package com.noelon.dadjokes_intermediate.database

import android.content.Context
import androidx.room.*

@Database(entities = [JokeDatabaseClass::class], version = 1, exportSchema = false)
@TypeConverters(value = [(RoomTypeConverters::class)])
abstract class JokeDataBase: RoomDatabase(){

    abstract val jokeDao: JokeDatabaseDao

    companion object{
        @Volatile
        var INSTANCE: JokeDataBase? = null

        fun getInstance(context: Context): JokeDataBase? {
            var instance = INSTANCE
            if (instance == null){
                instance = Room.databaseBuilder(context.applicationContext, JokeDataBase::class.java, "namesDatabase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}