package com.noelon.dadjokes_intermediate.ui.jokes_fragment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noelon.dadjokes_intermediate.database.JokeDataBase
import com.noelon.dadjokes_intermediate.network.JokesRetrofitObject
import com.noelon.dadjokes_intermediate.network.jokes.Joke
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException

class JokesViewModel(context: Context) : ViewModel() {
    private val uiScope = CoroutineScope(Dispatchers.Main)
    private val jokeDataBase: JokeDataBase? by lazy {
        JokeDataBase.getInstance(context)
    }

    // Using a Map instead of an ArrayList to avoid having the same joke twice by checking the id.
    private var _mapOfJokes = MutableLiveData(LinkedHashMap<Int?, Joke>())
    val mapOfJokes: LiveData<LinkedHashMap<Int?, Joke>>
        get() = _mapOfJokes

    private var _likedJokes = MutableLiveData(ArrayList<Joke?>())
    val likedJokes: LiveData<ArrayList<Joke?>>
        get() = _likedJokes

    init {
        getLikedJokes()
        getTenJokes()
        getTenJokes()
    }

    private fun getLikedJokes() {
        CoroutineScope(Dispatchers.IO).launch {
            val localLikedJokes = ArrayList<Joke?>()
            jokeDataBase?.jokeDao?.getAllLocalJokesDatabaseClass()?.toMutableList()
                ?.filter { it.isLiked }
                ?.forEach {
                    localLikedJokes.add(it.joke)
                }

            withContext(Dispatchers.Main) {
                Timber.d("Liked jokes: $localLikedJokes")
                _likedJokes.value = localLikedJokes
            }
        }
    }

    fun getTenJokes() {
        uiScope.launch {
            val innerHashMap = LinkedHashMap<Int?, Joke>()

            getTenJokesAsync()?.forEach {
                innerHashMap[it.id] = it
            }
            _mapOfJokes.value = innerHashMap
        }
    }

    private suspend fun getTenJokesAsync(): List<Joke>? {
        return withContext(Dispatchers.Default) {
            try {
                val remoteListOfJoke =
                    JokesRetrofitObject.jokesRetrofitService.getJokesAsync().await()

                remoteListOfJoke
            } catch (uhe: UnknownHostException) {
                Timber.e(uhe, "No internet")
                getLocalJokes()
            } catch (httpException: HttpException) {
                Timber.e(httpException, "Problem with request.")
                getLocalJokes()
            } catch (e: Throwable) {
                throw e
            }
        }
    }


    private suspend fun getLocalJokes(): List<Joke>? {
        return withContext(Dispatchers.IO) {
            val listOfJoke = ArrayList<Joke>()
            jokeDataBase?.jokeDao?.getAllLocalJokesDatabaseClass()?.forEach {
                it.joke?.let { joke -> listOfJoke.add(joke) }
            }
            listOfJoke.toList()
        }
    }
}