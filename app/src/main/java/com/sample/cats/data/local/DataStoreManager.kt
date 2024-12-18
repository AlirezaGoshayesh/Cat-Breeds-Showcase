package com.sample.cats.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "favorites")

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) :
    LocalDataSource {

    companion object {
        private val FAVORITE_IDS_KEY = stringSetPreferencesKey("favorite_ids")
    }

    // Function to save favorite ID
    override suspend fun saveFavorite(id: String) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITE_IDS_KEY] ?: emptySet()
            preferences[FAVORITE_IDS_KEY] = currentFavorites + id
        }
    }

    // Function to remove favorite ID
    override suspend fun removeFavorite(id: String) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITE_IDS_KEY] ?: emptySet()
            preferences[FAVORITE_IDS_KEY] = currentFavorites - id
        }
    }

    // Function to get all favorite IDs
    override fun getFavoriteIds(): Flow<Set<String>> {
        return context.dataStore.data.map { preferences ->
            preferences[FAVORITE_IDS_KEY] ?: emptySet()
        }
    }
}
