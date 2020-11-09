package com.noelon.dadjokes_intermediate.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.noelon.dadjokes_intermediate.network.jokes.Joke
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://official-joke-api.appspot.com/"

// https://official-joke-api.appspot.com/random_ten

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

var logging = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.HEADERS
}

val httpClient = OkHttpClient.Builder().apply {
    this.addInterceptor(logging)
}

val jokesRetrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(httpClient.build())
    .build()

interface JokeApiService {
    @GET("random_ten")
    fun getJokesAsync(): Deferred<List<Joke>>
}

object JokesRetrofitObject {
    val jokesRetrofitService: JokeApiService by lazy {
        jokesRetrofit.create(JokeApiService::class.java)
    }
}




