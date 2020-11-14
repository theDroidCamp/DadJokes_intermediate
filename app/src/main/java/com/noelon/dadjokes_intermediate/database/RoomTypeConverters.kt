package com.noelon.dadjokes_intermediate.database

import androidx.room.TypeConverter
import com.noelon.dadjokes_intermediate.network.jokes.Joke
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class RoomTypeConverters {
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val jokeJsonAdapter: JsonAdapter<Joke> = moshi.adapter(Joke::class.java)

    @TypeConverter
    fun stringToJoke(data: String?): Joke? {
        return jokeJsonAdapter.fromJson(data ?: return null)
    }

    @TypeConverter
    fun jokeToString(joke: Joke?): String? {
        return jokeJsonAdapter.toJson(joke ?: return null)
    }
}