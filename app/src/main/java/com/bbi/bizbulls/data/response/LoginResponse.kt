package com.bbi.bizbulls.data.response

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("success")
    var isSuccess = false

    @SerializedName("data")
    var data: Data? = null

    @SerializedName("message")
    var message: String? = null

    inner class Data {
        @SerializedName("token")
        var token: String? = null

        @SerializedName("data")
        var datas: Datas? = null

        inner class Datas {
            @SerializedName("first_name")
            var first_name: String? = null

            @SerializedName("last_name")
            var last_name: String? = null

            @SerializedName("email")
            var email: String? = null

            @SerializedName("mobile_no")
            var mobile_no: String? = null

            @SerializedName("user_id")
            var user_id = 0

            @SerializedName("registration_date")
            var registration_date: String? = null
        }
    }
}