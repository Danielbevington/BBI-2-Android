package com.bbi.bizbulls

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.view.View
import com.bbi.bizbulls.utils.MyProcessDialog
import com.google.gson.JsonObject
import com.bbi.bizbulls.data.signin.SigninResponse
import com.bbi.bizbulls.databinding.ActivityForgotpasswordBinding
import com.bbi.bizbulls.remote.RetrofitClient
import com.bbi.bizbulls.utils.CommonUtils
import com.bbi.bizbulls.utils.InputValidator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener {
    var binding: ActivityForgotpasswordBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotpasswordBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        init()
    }

    fun init() {
        binding!!.txtforgotweblink.setOnClickListener(this)
        binding!!.submitsendotp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view === binding!!.txtforgotweblink) {
            val uriUrl = Uri.parse(this@ForgotPasswordActivity.getString(R.string.bizbullsindia_url))
            val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
            launchBrowser.addCategory(Intent.CATEGORY_BROWSABLE)
            startActivity(launchBrowser)
        } else if (view === binding!!.submitsendotp) {
            if (InputValidator.isValidPhone(binding!!.etUsername.text.toString()) ||
                InputValidator.isValidEmail(binding!!.etUsername.text.toString())
            ) {
                resetPassword()
            } else {
                CommonUtils.toast(
                    this,
                    "Please type correct user name as mobile number or email address!"
                )
            }
        }
    }

    private fun resetPassword() {
        MyProcessDialog.showProgressBar(this, 0)
        val jsonObject = JsonObject()
        jsonObject.addProperty("email", binding!!.etUsername.text.toString().trim { it <= ' ' })
        val call: Call<SigninResponse> = RetrofitClient.getUrl().reset( jsonObject)
        call.enqueue(object : Callback<SigninResponse> {
            override fun onResponse(
                call: Call<SigninResponse>,
                response: Response<SigninResponse>
            ) {
                if (response.code() == 200) {
                    if (response.body()!!.expiresIn!! > 0) {
                        val fp = Intent(
                            this@ForgotPasswordActivity,
                            CreateNewPasswordActivity::class.java
                        )
                        fp.putExtra("username", binding!!.etUsername.text.toString())
                        startActivity(fp)
                        finish()
                    } else {
                        CommonUtils.toast(this@ForgotPasswordActivity, this@ForgotPasswordActivity.resources.getString(R.string.network_error))
                    }
                } else {
                    CommonUtils.toast(this@ForgotPasswordActivity, this@ForgotPasswordActivity.resources.getString(R.string.network_error))
                }
                MyProcessDialog.dismiss()
            }

            override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                MyProcessDialog.dismiss()
                RetrofitClient.showFailedMessage(this@ForgotPasswordActivity, t)
            }
        })
    }
}