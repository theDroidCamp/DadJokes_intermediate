package com.noelon.dadjokes_intermediate.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val firebaseAuth = FirebaseAuth.getInstance()
val fireStore = Firebase.firestore