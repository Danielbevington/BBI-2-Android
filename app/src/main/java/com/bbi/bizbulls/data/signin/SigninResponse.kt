package com.bbi.bizbulls.data.signin

import com.google.gson.annotations.SerializedName

class SigninResponse {
    @SerializedName("access_token")
    val accessToken: String? = null

    @SerializedName("refresh_token")
    val refreshToken: String? = null

    @SerializedName("token_type")
    val tokenType: String? = null

    @SerializedName("expires_in")
    val expiresIn: Int? = null
}