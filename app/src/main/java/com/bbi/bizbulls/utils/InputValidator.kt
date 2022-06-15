package com.bbi.bizbulls.utils

import android.text.TextUtils
import android.util.Patterns

/**
 * Created by Daniel.
 */
object InputValidator {

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && target?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() } == true
    }

    fun isValidPhone(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && target?.let { Patterns.PHONE.matcher(it).matches() } == true
    }
    fun isNullOrEmpty(inputString: String): Boolean {
        return TextUtils.isEmpty(inputString.trim())
    }

    fun isNumeric(inputString: String): Boolean {
        return TextUtils.isDigitsOnly(inputString)
    }
}