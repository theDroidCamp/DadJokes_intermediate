package com.noelon.dadjokes_intermediate.utils

import android.content.Context
import androidx.datastore.preferences.createDataStore

class SettingsDataStore(context: Context) {
    private val dataStore = context.createDataStore("settings")

    // Add Dark Mode
}