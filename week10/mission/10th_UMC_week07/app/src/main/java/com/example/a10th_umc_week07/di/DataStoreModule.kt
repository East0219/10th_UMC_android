package com.example.a10th_umc_week07.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.a10th_umc_week07.data.model.HomeData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "ex_name")

@Singleton
class DataStoreModule @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val WISH_LIST_KEY = stringPreferencesKey("wish_list")
    private val gson = Gson()

    suspend fun saveName(wishList: List<HomeData>) {
        context.dataStore.edit { settings ->
            val jsonString = gson.toJson(wishList)
            settings[WISH_LIST_KEY] = jsonString
        }
    }

    fun getName(): Flow<List<HomeData>> {
        return context.dataStore.data.map { preferences ->
            val jsonString = preferences[WISH_LIST_KEY] ?: ""
            val type = object : TypeToken<List<HomeData>>() {}.type
            gson.fromJson(jsonString, type) ?: emptyList()
        }
    }
}