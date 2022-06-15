package com.bbi.bizbulls.remote

import com.google.gson.JsonObject
import com.bbi.bizbulls.data.signupresponse.SignupResponse
import com.bbi.bizbulls.data.signin.SigninResponse
import com.bbi.bizbulls.data.franchiseregresponse.personaldetailscreate.PersonalDetailsSaveResponse
import com.bbi.bizbulls.data.health.HealthDetailsSaveResponse
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @POST("/api/register")
    fun register(@Body model: JsonObject): Call<SignupResponse>

    @POST("/oauth/token")
    fun login(@Body jsonObject: JsonObject): Call<SigninResponse>

    @POST("/api/passwords/reset")
    fun reset(@Body jsonObject: JsonObject): Call<SigninResponse>

    @POST("/api/me/personal")
    @FormUrlEncoded
    fun personalDetailsPost(
        @Header("Authorization") token: String,
        @FieldMap data: Map<String, String>
    ): Call<PersonalDetailsSaveResponse>

    @POST("/api/me/health")
    fun healthDetailsPost(
        @Header("Authorization") token: String,
        @Body jsonObject: JsonObject
    ): Call<HealthDetailsSaveResponse>
}