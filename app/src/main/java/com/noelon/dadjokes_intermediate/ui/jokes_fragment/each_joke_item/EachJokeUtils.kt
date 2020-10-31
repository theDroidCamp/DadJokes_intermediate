package com.noelon.dadjokes_intermediate.ui.jokes_fragment.each_joke_item

import com.noelon.dadjokes_intermediate.database.JokeDatabaseClass
import com.noelon.dadjokes_intermediate.database.JokeDatabaseDao
import com.noelon.dadjokes_intermediate.network.jokes.Joke


fun addLikedJokeToRoom(jokeDataBaseDao: JokeDatabaseDao?, joke: Joke) {
    val jokeDatabaseClass = JokeDatabaseClass(joke.id, true, joke)
    jokeDataBaseDao?.editJokeDatabaseClass(jokeDatabaseClass)
}

fun removeLikedJokeFromRoom(jokeDataBaseDao: JokeDatabaseDao?, joke: Joke) {
    val jokeDatabaseClass = JokeDatabaseClass(joke.id, false, joke)
    jokeDataBaseDao?.editJokeDatabaseClass(jokeDatabaseClass)
}