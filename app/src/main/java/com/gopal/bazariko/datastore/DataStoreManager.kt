package com.gopal.bazariko.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension property to create DataStore
val Context.dataStore by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {

    companion object {
        private val IS_FIRST_TIME = booleanPreferencesKey("is_first_time")
    }

    // Save value
    suspend fun saveIsFirstTime(value: Boolean) {
        context.dataStore.edit { settings ->
            settings[IS_FIRST_TIME] = value
        }
    }

    // Read value
    val isFirstTime: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_FIRST_TIME] ?: true // default = true
        }
}
