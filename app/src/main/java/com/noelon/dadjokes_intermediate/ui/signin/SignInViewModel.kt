package com.noelon.dadjokes_intermediate.ui.signin

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noelon.dadjokes_intermediate.R
import com.noelon.dadjokes_intermediate.models.ResultWrapper
import com.noelon.dadjokes_intermediate.models.User
import com.noelon.dadjokes_intermediate.repository.authRepository
import com.noelon.dadjokes_intermediate.ui.signup.SignUpFragment

class SignInViewModel: ViewModel() {
    val errorString = MutableLiveData<Int>()
    private val _user = MutableLiveData(User())
    val user: LiveData<com.noelon.dadjokes_intermediate.models.User>
        get() = _user
    var signInResponse = MutableLiveData<ResultWrapper<User>>()
    fun signIn(){
        val currentUser = _user.value
        if (checkInputValidation()){
            authRepository.signIn(currentUser!!, signInResponse)
        }
    }
    private fun checkInputValidation(): Boolean {
        val currentUser = _user.value
        if (currentUser != null) {
            val noEmptyField =
                    currentUser.email.isNotBlank() &&
                    currentUser.password.isNotBlank()

            if (!noEmptyField) {
                errorString.value = R.string.error_missing_field
                return false
            }

            val validEmail = Patterns.EMAIL_ADDRESS.matcher(currentUser.email).matches()
            if (!validEmail) {
                errorString.value = R.string.invalid_mail
                return false
            }

            val validPassword = currentUser.password.length >= 6
            if (!validPassword) {
                errorString.value = R.string.invalid_password
                return false
            }
            return true
        } else
            return false
    }

}