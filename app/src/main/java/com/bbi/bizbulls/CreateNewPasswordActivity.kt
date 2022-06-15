package com.bbi.bizbulls

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.text.method.PasswordTransformationMethod
import android.text.method.HideReturnsTransformationMethod
import android.view.View
import com.bbi.bizbulls.R
import android.widget.Toast
import com.bbi.bizbulls.databinding.ActivityCreatenewpasswordBinding
import java.lang.Exception

class CreateNewPasswordActivity : AppCompatActivity(), View.OnClickListener {
    var binding: ActivityCreatenewpasswordBinding? = null
    var username: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatenewpasswordBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)
        username = intent.getStringExtra("username")
        init()
    }

    fun init() {
        binding!!.txtcreateweblink.setOnClickListener(this)
        binding!!.submitresetpwd.setOnClickListener(this)
        binding!!.ivShowpBtn.setOnClickListener(this)
        binding!!.ivShowcBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view === binding!!.txtcreateweblink) {
            val uriUrl = Uri.parse("https://www.bizbullsindia.com")
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
        if (view === binding!!.ivShowcBtn) {
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
        if (view === binding!!.submitresetpwd) {
            val password = binding!!.etPassword.text.toString().trim { it <= ' ' }
//            if (validatesmallletter(password) && validateupparcase(password)
//                && validatenumber(password) && validatespecialsymbbol(password)
//            ) {
             //   if (validatechar(password)) {
                    if (binding!!.etPassword.text.toString() == binding!!.etConformPassword.text.toString()) {
                        try {
//
                        } catch (e: Exception) {
                            e.message
                        }
//                    } else {
//                        Toast.makeText(this, "Password mismatch!", Toast.LENGTH_SHORT).show()
//                    }
                }
            }
//        else {
//                Toast.makeText(
//                    this, """
//     Minimum one small character Required,
//     Minimum one upparcase character Required,
//     Minimum one number Required,
//     Minimum one special symbol Required
//     """.trimIndent(), Toast.LENGTH_LONG
//                ).show()
//            }
        }
    }

//    fun validatesmallletter(pws: String): Boolean {
//        val numberpattern = "(.*[a-z].*)"
//        var istrue = false
//        if (pws.matches(numberpattern)) {
//            istrue = true
//        } else {
//        }
//        return istrue
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

//    fun validatechar(pws: String): Boolean {
//        var istrue = false
//        if (pws.length < 7) {
//            Toast.makeText(this@, "Minimum 8 Character Required!", Toast.LENGTH_SHORT).show()
//        } else if (pws.length > 10) {
//            istrue = false
//            Toast.makeText(this, "Maximum 10 Character Required!", Toast.LENGTH_SHORT).show()
//        } else {
//            istrue = true
//        }
//        return istrue
//    }

//    fun validatenumber(pws: String): Boolean {
//        val numberpattern = "(.*[0-9].*)"
//        return pws.matches(numberpattern)
//    }

