package com.noelon.dadjokes_intermediate.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.noelon.dadjokes_intermediate.models.ResultWrapper
import com.noelon.dadjokes_intermediate.models.User
import javax.inject.Inject

val authRepository by lazy {
    AuthRepository(FirebaseAuth.getInstance())
}

class AuthRepository(private val auth: FirebaseAuth) {
    private val TAG = AuthRepository::class.simpleName

    fun signUp(user: User, response: MutableLiveData<ResultWrapper<User>>) {

        auth.createUserWithEmailAndPassword(user.email, user.password)

            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = auth.currentUser
                    user.uid = firebaseUser?.uid ?: ""
                    createUser(user)

                    response.postValue(ResultWrapper.Success(user))

                } else {
                    // If sign in fails, display a message to the user.
                    response.postValue(
                        ResultWrapper.GenericError(null, task.exception.toString()))
                }
            }
    }

    private fun createUser(user: User) {

        val database = Firebase.firestore
        database.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.d(TAG, e.toString())
            }
    }
}