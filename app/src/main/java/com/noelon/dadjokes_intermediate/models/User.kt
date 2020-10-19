package com.noelon.dadjokes_intermediate.models

data class User(
    var uid: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = ""
)