package com.noelon.dadjokes_intermediate.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noelon.dadjokes_intermediate.models.User

class SignUpViewModel : ViewModel() {
    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User>
    get() = _user
}