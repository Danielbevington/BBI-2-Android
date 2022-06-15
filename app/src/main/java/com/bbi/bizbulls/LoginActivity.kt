package com.bbi.bizbulls

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bbi.bizbulls.utils.MyProcessDialog
import com.bbi.bizbulls.data.signin.SigninResponse
import com.bbi.bizbulls.databinding.ActivityLoginBinding
import com.bbi.bizbulls.remote.RetrofitClient
import com.bbi.bizbulls.sharedpref.SharedPrefsManager
import com.bbi.bizbulls.utils.CommonUtils
import com.bbi.bizbulls.utils.InputValidator
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val sharedPrefsHelper by lazy { SharedPrefsManager(this@LoginActivity) }
    var binding: ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        init()
    }

    fun init() {
        binding!!.txtloginweblink.setOnClickListener(this)
        binding!!.ivShowpBtn.setOnClickListener(this)
        binding!!.submitlogin.setOnClickListener(this)
        binding!!.tvFgpassword.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view === binding!!.txtloginweblink) {
            val uriUrl = Uri.parse(this@LoginActivity.getString(R.string.bizbullsindia_url))
            val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
            launchBrowser.addCategory(Intent.CATEGORY_BROWSABLE)
            startActivity(launchBrowser)
        }
        if (view === binding!!.ivShowpBtn) {
            if (binding!!.etPassword.transformationMethod == PasswordTransformationMethod.getInstance()) {
                binding!!.etPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding!!.ivShowpBtn.setImageResource(R.drawable.ic_visibility)
            } else {
                binding!!.ivShowpBtn.setImageResource(R.drawable.ic_visibility_off)
                binding!!.etPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        }
        if (view === binding!!.submitlogin) {
            if (CommonUtils.isNetworkConnected(this@LoginActivity)) {
                if (InputValidator.isValidPhone(binding!!.etUsername.text.toString()) ||
                    InputValidator.isValidEmail(binding!!.etUsername.text.toString())
                ) {
                    if (binding!!.etPassword.text.toString().isNotEmpty()) {
                        login()
                    } else {
                        Toast.makeText(this, "Please type correct password!", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Please type correct user name as mobile number or email address!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                CommonUtils.toast(this, "Please check your network connection.")
            }
        }
        if (view === binding!!.tvFgpassword) {
            val fp = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(fp)
        }
    }

    private fun login() {
        MyProcessDialog.showProgressBar(this, 0)
        val jsonObject = JsonObject()
        jsonObject.addProperty("grant_type", "password")
        jsonObject.addProperty("client_id", "2")
        jsonObject.addProperty("client_secret", "Uc54V4JnJ7fw95UwZ9IWoRxQafMh0TZy2f5L83kI")
        jsonObject.addProperty("username", binding!!.etUsername.text.toString().trim { it <= ' ' })
        jsonObject.addProperty("password", binding!!.etPassword.text.toString().trim { it <= ' ' })
        val call: Call<SigninResponse> = RetrofitClient.getUrl().login( jsonObject)
            call.enqueue(object : Callback<SigninResponse> {
            override fun onResponse(
                call: Call<SigninResponse>,
                response: Response<SigninResponse>
            ) {
                if (response.code() == 200) {
                    if (response.body()!!.expiresIn!! > 0) {
                        sharedPrefsHelper.isLogin = true
                        sharedPrefsHelper.authToken = response.body()?.tokenType + " " + response.body()?.accessToken
                        MyProcessDialog.dismiss()
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        finish()
                        val i = Intent(this@LoginActivity, DashboardActivity::class.java)
                        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(i)
                        CommonUtils.toast(this@LoginActivity, "Login Successfully!")
                    } else {
                        CommonUtils.toast(this@LoginActivity, this@LoginActivity.resources.getString(R.string.network_error))
                    }
                } else {
                    CommonUtils.toast(this@LoginActivity, this@LoginActivity.resources.getString(R.string.network_error))
                }
                MyProcessDialog.dismiss()
            }

            override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                MyProcessDialog.dismiss()
                RetrofitClient.showFailedMessage(this@LoginActivity, t)

            }
        })
    }
}