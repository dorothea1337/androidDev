package ru.mirea.ivanova.nasareport.data.storage

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsHelper(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUserEmail(email: String) {
        prefs.edit().putString("email", email).apply()
    }

    fun getUserEmail(): String? {
        return prefs.getString("email", null)
    }
}