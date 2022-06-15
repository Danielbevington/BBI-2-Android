package com.bbi.bizbulls.utils

import com.bbi.bizbulls.enums.Environment

/**
 * Singleton container for application constants and globals
 */
object Globals {

    // Base URLs by Environment
    private const val BASE_URL_PROD = "https://bizbulls.beurboss.com/"
    private const val BASE_URL_QA = "https://bizbulls.beurboss.com/"
    private const val BASE_URL_DEV = "https://bizbulls.beurboss.com/"

    val BASE_URL = when (CommonUtils.environment) {
        Environment.PRODUCTION -> BASE_URL_PROD
        Environment.QA -> BASE_URL_QA
        Environment.DEVELOPMENT -> BASE_URL_DEV
    }


    const val APPLICATION_NAME = "Biz Bulls"
    var See_All_Type = 2
    var Item_Type = 1
    var SEE_ALL_TAG = "See All>>"

    const val SELECT = "Select"
    const val SELECT_HAND_USE = "Select regular hand usage"
    const val SELECT_BLOOD_GROUP = "Select your blood group"
    const val SELECT_PH = "Physically handicapped?"
    const val SELECT_SURGERY = "Any Surgeries/Treatments Undergone?"
    const val SELECT_HEALTHISSUES = "Any other Health Issues?"
    const val SELECT_UNHEALTHHABITS = "Any unhealthy habits?"
    const val SELECT_ACCOUNT_TYPE = "Select bank account type"
    const val SELECT_BUSINESSPLACETYPE = "Select business place type"
    const val OWNED = "Owned"
    const val LEASED = "Leased"
    const val RENTED = "Rented"
    const val SAVING = "Saving"
    const val CURRENT = "Current"

    const val PVTLIMITED = "Pvt Limited"
    const val PARTNERSHIP = "Partnership"
    const val PROPRIETOR = "Proprietor"
    const val FOFO = "FOFO"
    const val FOCO = "FOCO"
    const val COFO = "COFO"
    var SHARED_PREFERENCES = "SHARED_PREFERENCES"
    var PREF_AUTH_KEY = "PREF_AUTH_KEY"
    var App_id = "App_id"
    var email = "email"
    var mobile = "mobile"
    var role_id = "role_id"
    var name = "name"
    var KEY = "key"
    var SECRET = "secret"
    var REGION = "region"
    var BUCKET = "bucket"
    var S3_URL = "s3_url"
}
