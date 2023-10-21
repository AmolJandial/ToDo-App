package com.yepyuno.todolist.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.yepyuno.todolist.util.Constants.Companion.DEFAULT_IS_SYNCED
import com.yepyuno.todolist.util.Constants.Companion.DEFAULT_USERNAME
import com.yepyuno.todolist.util.Constants.Companion.PREFERENCE_IS_SYNCED
import com.yepyuno.todolist.util.Constants.Companion.PREFERENCE_NAME
import com.yepyuno.todolist.util.Constants.Companion.PREFERENCE_USERNAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private object PreferenceKeys{
        val username = stringPreferencesKey(PREFERENCE_USERNAME)
        val isSynced = booleanPreferencesKey(PREFERENCE_IS_SYNCED)
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = PREFERENCE_NAME
    )

    suspend fun saveUser(username: String, isSynced: Boolean){
        context.dataStore.edit {preferences ->
            preferences[PreferenceKeys.username] = username
            preferences[PreferenceKeys.isSynced] = isSynced
        }
    }

    suspend fun getUser() = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val username = preferences[PreferenceKeys.username] ?: DEFAULT_USERNAME
            val isSynced = preferences[PreferenceKeys.isSynced] ?: DEFAULT_IS_SYNCED
            User(username, isSynced)
        }
        .first()

}

data class User(
    val username: String,
    val isSynced: Boolean
)