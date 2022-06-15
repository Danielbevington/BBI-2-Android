package com.bbi.bizbulls

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import com.bbi.bizbulls.utils.MyProcessDialog
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.graphics.Color
import android.text.method.PasswordTransformationMethod
import android.text.method.HideReturnsTransformationMethod
import com.google.gson.JsonObject
import com.bbi.bizbulls.data.signupresponse.SignupResponse
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import com.google.android.material.textfield.TextInputEditText
import android.text.TextWatcher
import android.text.Editable
import android.view.View
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.bbi.bizbulls.databinding.ActivitySignupBinding
import com.bbi.bizbulls.remote.RetrofitClient
import com.bbi.bizbulls.sharedpref.SharedPrefsManager
import com.bbi.bizbulls.utils.CommonUtils
import com.bbi.bizbulls.utils.InputValidator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity(), View.OnClickListener {
    private val sharedPrefsHelper by lazy { SharedPrefsManager(this@SignupActivity) }
    var binding: ActivitySignupBinding? = null
    var otpdialog: Dialog? = null
    var isFill = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        init()
    }

    override fun onClick(view: View) {
        if (view === binding!!.submitsignup) {
            if (CommonUtils.isNetworkConnected(this@SignupActivity)) {
                val password = binding!!.etPassword.text.toString().trim { it <= ' ' }
                if (!InputValidator.isValidPhone(binding!!.etMobileNumber.text.toString())) {
                    binding!!.etMobileNumber.error = "Please enter valid mobile number"
                } else if (!InputValidator.isValidEmail(binding!!.etEmail.text.toString())) {
                    binding!!.etEmail.error = "Please enter valid email address"
                } else {
//                    if (validatesmallletter(password) && validateupparcase(password)
//                        && validatenumber(password) && validatespecialsymbbol(password)
//                    ) {
                        if (validatechar(password)) {
                            if (binding!!.etPassword.text.toString() == binding!!.etConformPassword.text.toString()) {
                                signupApi()
                            } else {
                                binding!!.etConformPassword.error = "Password mismatch!"
                            }
                      //  }
                    } else {
                        Toast.makeText(
                            this, """
     Minimum one small character Required,
     Minimum one upparcase character Required,
     Minimum one number Required,
     Minimum one special symbol Required
     """.trimIndent(), Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                CommonUtils.toast(this, "Please check your network connection.")
            }
        } else if (view === binding!!.txtbizbulllink) {
            val uriUrl = Uri.parse("https://www.bizbullsindia.com")
            val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
            launchBrowser.addCategory(Intent.CATEGORY_BROWSABLE)
            startActivity(launchBrowser)
        } else if (view === binding!!.tvLogin) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else if (view === binding!!.ivShowpBtn) {
            if (binding!!.etPassword.transformationMethod == PasswordTransformationMethod.getInstance()) {
                binding!!.etPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding!!.ivShowpBtn.setImageResource(R.drawable.ic_visibility)
            } else {
                binding!!.ivShowpBtn.setImageResource(R.drawable.ic_visibility_off)
                binding!!.etPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        } else if (view === binding!!.ivShowcBtn) {
            if (binding!!.etConformPassword.transformationMethod == PasswordTransformationMethod.getInstance()) {
                binding!!.etConformPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding!!.ivShowcBtn.setImageResource(R.drawable.ic_visibility)
            } else {
                binding!!.ivShowcBtn.setImageResource(R.drawable.ic_visibility_off)
                binding!!.etConformPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        }
    }

    private fun signupApi() {
        val jsonObject = JsonObject()
        jsonObject.addProperty("name", binding!!.etUsername.text.toString().trim { it <= ' ' })
        jsonObject.addProperty("email", binding!!.etEmail.text.toString().trim { it <= ' ' })
        jsonObject.addProperty("phone", binding!!.etMobileNumber.text.toString().trim { it <= ' ' })
        jsonObject.addProperty("password", binding!!.etPassword.text.toString().trim { it <= ' ' })
        jsonObject.addProperty(
            "password_confirmation",
            binding!!.etConformPassword.text.toString().trim { it <= ' ' })
        MyProcessDialog.showProgressBar(this, 0)
        val call: Call<SignupResponse> = RetrofitClient.getUrl().register( jsonObject)
        call.enqueue(object : Callback<SignupResponse> {
            override fun onResponse(
                call: Call<SignupResponse>,
                response: Response<SignupResponse>
            ) {
                if (response.code() == 201) {
                    if (!response.body()!!.data?.id?.isEmpty()!!) {
                        sharedPrefsHelper.tokenID = response.body()?.data?.id.toString()
                        sharedPrefsHelper.userFirstName = response.body()?.data?.name.toString()
                        sharedPrefsHelper.userLastName = response.body()?.data?.name.toString()

                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        MyProcessDialog.dismiss()
                        CommonUtils.toast(this@SignupActivity, "Registration Successfully!")
                        val i = Intent(this@SignupActivity, LoginActivity::class.java)
                        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(i)
                        finish()
                    } else {
                        MyProcessDialog.dismiss()
                        CommonUtils.toast(this@SignupActivity, "Something went wrong.")
                    }
                } else {
                    MyProcessDialog.dismiss()
                    CommonUtils.toast(this@SignupActivity, "Something went wrong.")
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                MyProcessDialog.dismiss()
                RetrofitClient.showFailedMessage(this@SignupActivity, t)
            }
        })
    }

    fun init() {
        binding!!.tvLogin.setOnClickListener(this)
        binding!!.ivShowpBtn.setOnClickListener(this)
        binding!!.ivShowcBtn.setOnClickListener(this)
        binding!!.txtbizbulllink.setOnClickListener(this)
        binding!!.submitsignup.setOnClickListener(this)
    }

    fun showOtpVerification() {
        otpdialog = Dialog(this)
        otpdialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        otpdialog!!.setCancelable(false)
        otpdialog!!.setContentView(R.layout.dialog_otp)
        otpdialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val ed1: TextInputEditText
        val ed2: TextInputEditText
        val ed3: TextInputEditText
        val ed4: TextInputEditText
        val ed5: TextInputEditText
        val ed6: TextInputEditText
        ed1 = otpdialog!!.findViewById(R.id.ed_1)
        ed2 = otpdialog!!.findViewById(R.id.ed_2)
        ed3 = otpdialog!!.findViewById(R.id.ed_3)
        ed4 = otpdialog!!.findViewById(R.id.ed_4)
        ed5 = otpdialog!!.findViewById(R.id.ed_5)
        ed6 = otpdialog!!.findViewById(R.id.ed_6)
        ed1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (editable.toString().length > 0) ed2.requestFocus()
            }
        })
        ed2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (editable.toString().length > 0) ed3.requestFocus() else ed1.requestFocus()
            }
        })
        ed3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                //onHideView(tvError);
                if (editable.toString().length > 0) ed4.requestFocus() else ed2.requestFocus()
            }
        })
        ed4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                //onHideView(tvError);
                if (editable.toString().length > 0) ed5.requestFocus() else ed2.requestFocus()
            }
        })
        ed5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                //onHideView(tvError);
                if (editable.toString().length > 0) ed6.requestFocus() else ed2.requestFocus()
            }
        })
        ed6.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                //onHideView(tvError);
                if (editable.toString().length > 0) {
                    isFill = true
                } else {
                    isFill = false
                    ed5.requestFocus()
                }
            }
        })
        val submitverify = otpdialog!!.findViewById<AppCompatButton>(R.id.submitverify)
        submitverify.setOnClickListener { otpdialog!!.cancel() }
        otpdialog!!.show()
    }

    private fun isEmpty(etText: AppCompatEditText): Boolean {
        return etText.text.toString().trim { it <= ' ' }.isEmpty()
    }

    private fun validatechar(pws: String): Boolean {
        var istrue = false
        if (pws.length < 7) {
            Toast.makeText(this, "Minimum 8 Character Required!", Toast.LENGTH_SHORT).show()
        } else if (pws.length > 10) {
            istrue = false
            Toast.makeText(this, "Maximum 10 Character Required!", Toast.LENGTH_SHORT).show()
        } else {
            istrue = true
        }
        return istrue
    }

//    fun validatenumber(pws: String): Boolean {
//        val numberpattern = "(.*[0-9].*)"
//        return pws.matches(numberpattern)
//    }
//
//    fun validateupparcase(pws: String): Boolean {
//        val numberpattern = "(.*[A-Z].*)"
//        return pws.matches(numberpattern)
//    }
//
//    fun validatespecialsymbbol(pws: String): Boolean {
//        val numberpattern = "^(?=.*[_.()$&@]).*$"
//        return pws.matches(numberpattern)
//    }
//
//    fun validatesmallletter(pws: String): Boolean {
//        val numberpattern = "(.*[a-z].*)"
//        var istrue = false
//        if (pws.matches(numberpattern)) {
//            istrue = true
//        } else {
//        }
//        return istrue
//    }
}