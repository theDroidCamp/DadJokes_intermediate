package com.noelon.dadjokes_intermediate.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FirebaseModule {
    @Singleton
    @Provides
    fun provideAuth() = FirebaseAuth.getInstance()
    @Singleton
    @Provides
    fun provideFirestore() = FirebaseFirestore.getInstance()
}