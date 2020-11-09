package com.noelon.dadjokes_intermediate.network.jokes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Joke(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "punchline")
    val punchline: String?,
    @Json(name = "setup")
    val setup: String?,
    @Json(name = "type")
    val type: String?
)