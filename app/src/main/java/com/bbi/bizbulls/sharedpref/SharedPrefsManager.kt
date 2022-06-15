package com.bbi.bizbulls.sharedpref

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.bbi.bizbulls.LoginActivity
import com.bbi.bizbulls.utils.Globals
import com.bbi.bizbulls.R
import com.bbi.bizbulls.SignupActivity
import com.bbi.bizbulls.utils.CommonUtils

/**
 * Class used to save and read relatively small collection of key-values info,
 * which will be required for the application at any later point of time
 */
class SharedPrefsManager constructor(private val myContext: Context) {

    private val generalPrefPackageIdentifier = myContext.resources.getString(R.string.generalPrefPackageIdentifier)
    var sharedPrefs: SharedPreferences = myContext.getSharedPreferences(generalPrefPackageIdentifier, Context.MODE_PRIVATE)
        private set

    companion object {
        const val KEY_IS_LOGIN = "isLogin"
        const val KEY_AUTH_TOKEN = "authToken"
        const val KEY_TOKEN_ID = "tokenID"
        const val KEY_FIRST_NAME = "firstName"
        const val KEY_LAST_NAME = "lastName"
        const val KEY_EMAIL = "email"
        const val KEY_PHONE = "phone"
        const val KEY_PERSONAL_DETAIL_ID = "personalDetailID"


        const val DEFAULT_KEY_IS_LOGIN = false
    }

    var isLogin: Boolean
        get() = sharedPrefs[KEY_IS_LOGIN] ?: DEFAULT_KEY_IS_LOGIN
        set(value) {
            sharedPrefs[KEY_IS_LOGIN] = value
        }
    var authToken: String
        get() = sharedPrefs[KEY_AUTH_TOKEN] ?: ""
        set(value) {
            sharedPrefs[KEY_AUTH_TOKEN] = value
        }
    var tokenID: String
        get() = sharedPrefs[KEY_TOKEN_ID] ?: ""
        set(value) {
            sharedPrefs[KEY_TOKEN_ID] = value
        }
    var userFirstName: String
        get() = sharedPrefs[KEY_FIRST_NAME] ?: ""
        set(value) {
            sharedPrefs[KEY_FIRST_NAME] = value
        }
    var userLastName: String
        get() = sharedPrefs[KEY_LAST_NAME] ?: ""
        set(value) {
            sharedPrefs[KEY_LAST_NAME] = value
        }
    var email: String
        get() = sharedPrefs[KEY_EMAIL] ?: ""
        set(value) {
            sharedPrefs[KEY_EMAIL] = value
        }
    var phone: Int
        get() = sharedPrefs[KEY_PHONE] ?: 0
        set(value) {
            sharedPrefs[KEY_PHONE] = value
        }
    var personalDetailID: String
        get() = sharedPrefs[KEY_PERSONAL_DETAIL_ID] ?: ""
        set(value) {
            sharedPrefs[KEY_PERSONAL_DETAIL_ID] = value
        }


    /**
     * Extension function to listen the edit() and apply() function calls on every SharedPreferences operation
     *
     * @param operation This contains operation type with key and value pair
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * Creates or Updates a key value pair in SharedPrefs. Generic method that accepts any data type.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    private operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            null -> edit { it.remove(key) }
            else -> Log.e(Globals.APPLICATION_NAME, myContext.getString(R.string.unsupported_property_type))
        }
    }

    /**
     * This function retrieve saved values from shared preferences by referring key
     *
     * @param key The name of the preference to retrieve.
     * @param defaultValue If there is no any value related to referring key this will return this default value
     */
    private inline operator fun <reified T : Any> SharedPreferences.get(
        key: String, defaultValue: T? = null
    ): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: 0) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: 0) as T?
            else -> {
                Log.e(Globals.APPLICATION_NAME, myContext.getString(R.string.unsupported_property_type)); null
            }
        }
    }

    fun forceLogout(context: Context) {
        resetPreferences()
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
        CommonUtils.toast(context, context.resources.getString(R.string.logout))
    }

    private fun resetPreferences() {
        isLogin = false
        authToken = ""
        tokenID = ""
        userFirstName = ""
        userLastName = ""
        email = ""
        phone = 0
        personalDetailID = ""

    }

}