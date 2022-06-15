package com.bbi.bizbulls.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.bbi.bizbulls.R

@SuppressLint("StaticFieldLeak")
object MyProcessDialog {
    private var myAlertDialog: AlertDialog? = null
    private var bar: ProgressBar? = null
    private var t001: TextView? = null
    private var circular: ProgressBar? = null
    fun showMessage(strText: String?) {
        circular!!.visibility = View.VISIBLE
        bar!!.visibility = View.GONE
        t001!!.text = strText
        myAlertDialog!!.show()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun showProgressBar(context: Context, size: Int) {
        myAlertDialog = AlertDialog.Builder(context, R.style.MyAlertDialogTheme).create()
        val loadView = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null)
        myAlertDialog!!.setView(loadView)
        myAlertDialog!!.setCanceledOnTouchOutside(false)
        circular = loadView.findViewById(R.id.progressCircular)
        t001 = loadView.findViewById(R.id.textView)
        bar = loadView.findViewById(R.id.progressBar)
        bar?.progressDrawable = context.getDrawable(R.drawable.progress_color)
        bar?.max = 100
        bar?.progress = 0
        circular?.visibility = View.GONE
        bar?.visibility = View.VISIBLE
        bar?.max = size
        bar?.progress = 0
        myAlertDialog!!.show()
    }

    fun addProgressBar(size: Int) {
        bar!!.incrementProgressBy(size)
    }

    fun dismiss() {
        if (myAlertDialog != null && myAlertDialog!!.isShowing) {
            myAlertDialog!!.dismiss()
        }
    }
}