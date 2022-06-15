package com.bbi.bizbulls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import com.bbi.bizbulls.ui.fragment.HomeCustomerFragment
import com.bbi.bizbulls.ui.fragment.CustomerFOStatusFragment
import android.os.Bundle
import com.google.android.material.progressindicator.LinearProgressIndicator
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.GravityCompat
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bbi.bizbulls.databinding.ActivityDashboardBinding
import com.bbi.bizbulls.sharedpref.SharedPrefsManager
import com.bbi.bizbulls.utils.CommonUtils

class DashboardActivity : AppCompatActivity(), View.OnClickListener {
    private val sharedPrefsHelper by lazy { SharedPrefsManager(this@DashboardActivity) }
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var binding: ActivityDashboardBinding? = null
    var myAccount: ConstraintLayout? = null
    var myRefer: ConstraintLayout? = null
    var myOffer: ConstraintLayout? = null
    var myWallet: ConstraintLayout? = null
    var about: ConstraintLayout? = null
    var termsAndCondition: ConstraintLayout? = null
    var privacyPolicy: ConstraintLayout? = null
    var registerComplaint: ConstraintLayout? = null
    var contactUs: ConstraintLayout? = null
    var followUs: ConstraintLayout? = null
    var rateUs: ConstraintLayout? = null
    var settings: ConstraintLayout? = null
    var logout: ConstraintLayout? = null
    var count = 0
    var homeCustomerFragment: HomeCustomerFragment? = null
    var customerFOStatusFragment: CustomerFOStatusFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        init()
        println("________NAME ::${sharedPrefsHelper.userFirstName}")
        println("________EMAIL  ::${sharedPrefsHelper.email}")
        println("________PHONE  ::${sharedPrefsHelper.phone}")
        println("________TOKEN_ID  ::${sharedPrefsHelper.tokenID}")
        println("________AUTH_TOKEN  ::${sharedPrefsHelper.authToken}")
    }

    fun init() {
        binding!!.bottomNavigationView.background = null
        binding!!.bottomNavigationView.setOnItemSelectedListener { item ->
            val id = item.itemId
            when (id) {
                R.id.navigation_fohome -> supportFragmentManager.beginTransaction()
                    .replace(R.id.flFragment, homeCustomerFragment!!).commit()
                R.id.navigation_forevenue -> supportFragmentManager.beginTransaction()
                    .replace(R.id.flFragment, customerFOStatusFragment!!).commit()
            }
            true
        }
        binding!!.navfo.bringToFront()
        val hView = binding!!.navfo.getHeaderView(0)
        val lm = hView.findViewById<LinearProgressIndicator>(R.id.progressBarprofileupdate)
        lm.progress = 25
        val nav_user = hView.findViewById<AppCompatTextView>(R.id.txtfoname)
        nav_user.text = sharedPrefsHelper.userFirstName
        val nav_mobile = hView.findViewById<AppCompatTextView>(R.id.txtfomobile)
        nav_mobile.text = sharedPrefsHelper.phone.toString()
        val nav_email = hView.findViewById<AppCompatTextView>(R.id.txtfoemail)
        nav_email.text = sharedPrefsHelper.email
        myAccount = hView.findViewById(R.id.myAccount)
        myAccount?.setOnClickListener(this)
        myRefer = hView.findViewById(R.id.myRefer)
        myRefer?.setOnClickListener(this)
        myOffer = hView.findViewById(R.id.myOffer)
        myOffer?.setOnClickListener(this)
        myWallet = hView.findViewById(R.id.myWallet)
        myWallet?.setOnClickListener(this)
        about = hView.findViewById(R.id.about)
        about?.setOnClickListener(this)
        termsAndCondition = hView.findViewById(R.id.termsAndCondition)
        termsAndCondition?.setOnClickListener(this)
        privacyPolicy = hView.findViewById(R.id.privacyPolicy)
        privacyPolicy?.setOnClickListener(this)
        registerComplaint = hView.findViewById(R.id.registerComplaint)
        registerComplaint?.setOnClickListener(this)
        contactUs = hView.findViewById(R.id.contactUs)
        contactUs?.setOnClickListener(this)
        followUs = hView.findViewById(R.id.followUs)
        followUs?.setOnClickListener(this)
        rateUs = hView.findViewById(R.id.rateUs)
        rateUs?.setOnClickListener(this)
        settings = hView.findViewById(R.id.settings)
        settings?.setOnClickListener(this)
        logout = hView.findViewById(R.id.logout)
        logout?.setOnClickListener(this)
        binding!!.layoutdraweropen.setOnClickListener(this)
        homeCustomerFragment = HomeCustomerFragment()
        customerFOStatusFragment = CustomerFOStatusFragment()
        binding!!.bottomNavigationView.selectedItemId = R.id.navigation_fohome
        loadFragment(homeCustomerFragment)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding!!.drawerfomainlayout,
            R.string.nav_open,
            R.string.nav_close
        )
        binding!!.drawerfomainlayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        binding!!.imgdrawer.setOnClickListener { view: View? ->
            if (binding!!.drawerfomainlayout.isDrawerOpen(GravityCompat.START)) {
                binding!!.drawerfomainlayout.closeDrawer(GravityCompat.START)
            } else {
                binding!!.drawerfomainlayout.openDrawer(GravityCompat.START)
            }
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.myAccount) {
        }
        if (view.id == R.id.myRefer) {
        }
        if (view.id == R.id.myOffer) {
        }
        if (view.id == R.id.myWallet) {
        }
        if (view.id == R.id.about) {
        }
        if (view.id == R.id.termsAndCondition) {
        }
        if (view.id == R.id.privacyPolicy) {
        }
        if (view.id == R.id.registerComplaint) {
        }
        if (view.id == R.id.contactUs) {
        }
        if (view.id == R.id.followUs) {
        }
        if (view.id == R.id.rateUs) {
        }
        if (view.id == R.id.settings) {
        }
        if (view.id == R.id.logout) {
            finish()
            sharedPrefsHelper.forceLogout(this@DashboardActivity)
        }
        if (view === binding!!.layoutdraweropen) {
            binding!!.drawerfomainlayout.openDrawer(Gravity.LEFT)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (count == 1) {
                finish()
            } else {
                Toast.makeText(this, "Press once again to exit!", Toast.LENGTH_LONG).show()
            }
            count++
            true
        } else super.onKeyDown(keyCode, event)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.flFragment, fragment).commit()
            return true
        }
        return false
    }

    companion object {
        private const val TAG = "Dashboard"
    }
}