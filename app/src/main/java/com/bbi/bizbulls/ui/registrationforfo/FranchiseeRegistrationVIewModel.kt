package com.bbi.bizbulls.ui.registrationforfo

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bbi.bizbulls.R
import com.bbi.bizbulls.data.franchiseregresponse.personaldetailscreate.PersonalDetailsSaveResponse
import com.bbi.bizbulls.data.health.HealthDetailsSaveResponse
import com.bbi.bizbulls.remote.RetrofitClient
import com.bbi.bizbulls.sharedpref.SharedPrefsManager
import com.bbi.bizbulls.ui.registrationforfo.fragments.FoPersonalDetailsFragment
import com.bbi.bizbulls.utils.CommonUtils
import com.bbi.bizbulls.utils.MyProcessDialog
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Daniel.
 */
class FranchiseeRegistrationVIewModel : ViewModel() {

    /**
     * Post Personal details
     */
    fun sendPersonalDetail(context: Context, params: MutableMap<String, String> = HashMap()) {
        val sharedPrefsHelper by lazy { SharedPrefsManager(context) }

        val call: Call<PersonalDetailsSaveResponse> =
            RetrofitClient.getUrl().personalDetailsPost(sharedPrefsHelper.authToken, params)
        println("________URL ::${call.request().url}")
        println("________Details $params")
        MyProcessDialog.showProgressBar(context, 0)
        call.enqueue(object : Callback<PersonalDetailsSaveResponse> {
            override
            fun onResponse(
                call: Call<PersonalDetailsSaveResponse>,
                responseObject: Response<PersonalDetailsSaveResponse>
            ) {
                if (responseObject.code() == 201) {
                    sharedPrefsHelper.personalDetailID = responseObject.body()?.data?.id.toString()
                    FranchiseeRegistrationActivity.activityCalling(context, context.resources.getString(R.string.personal_details))
                } else {
                    RetrofitClient.showResponseMessage(context, responseObject.code())

                }
                MyProcessDialog.dismiss()
            }

            override
            fun onFailure(call: Call<PersonalDetailsSaveResponse>, t: Throwable) {
                MyProcessDialog.dismiss()
                RetrofitClient.showFailedMessage(context, t)
            }
        })
    }

    /**
     * Post Health details
     */
    fun sendHealthDetail(context: Context, jsonObject: JsonObject) {
        val sharedPrefsHelper by lazy { SharedPrefsManager(context) }

        val call: Call<HealthDetailsSaveResponse> =
            RetrofitClient.getUrl().healthDetailsPost(sharedPrefsHelper.authToken, jsonObject)
        println("________URL ::${call.request().url}")
        println("________Details $jsonObject")
        MyProcessDialog.showProgressBar(context, 0)
        call.enqueue(object : Callback<HealthDetailsSaveResponse> {
            override
            fun onResponse(
                call: Call<HealthDetailsSaveResponse>,
                responseObject: Response<HealthDetailsSaveResponse>
            ) {
                if (responseObject.code() == 201) {
                    FranchiseeRegistrationActivity.activityCalling(context, context.resources.getString(R.string.health_details))
                } else {
                    RetrofitClient.showResponseMessage(context, responseObject.code())
                }
                MyProcessDialog.dismiss()
            }

            override
            fun onFailure(call: Call<HealthDetailsSaveResponse>, t: Throwable) {
                MyProcessDialog.dismiss()
                RetrofitClient.showFailedMessage(context, t)
            }
        })
    }

}