package com.bbi.bizbulls

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import com.bbi.bizbulls.databinding.ActivityProjectinfoBinding
import com.bbi.bizbulls.ui.registrationforfo.FoRegistrationDashBoardActivity

class ProjectInfoActivity : AppCompatActivity(), View.OnClickListener {
    var binding: ActivityProjectinfoBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectinfoBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.tvRegister.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view === binding!!.tvRegister) {
            finish()
            val i = Intent(this@ProjectInfoActivity, FoRegistrationDashBoardActivity::class.java)
            startActivity(i)
        }
    }
}