package com.bbi.bizbulls.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.TextUtils
import android.util.Patterns
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.BuildConfig
import com.bbi.bizbulls.databinding.DialogMessagesBinding
import com.bbi.bizbulls.enums.Environment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {

    /** Globally accessible build environment */
    val environment = when (BuildConfig.BUILD_TYPE) {
        "release" -> Environment.PRODUCTION
        "qa" -> Environment.QA
        else -> Environment.DEVELOPMENT
    }

    fun toast(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    @SuppressLint("SimpleDateFormat")
    fun commonDatePickerDialog(context: Context, datePickerListener: DatePickerListener) {
        //Get yesterday's date
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH] + 1
        val day = calendar[Calendar.DATE]
        val datePickerDialog = DatePickerDialog(context,
            { datePicker: DatePicker?, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                var date: String? =
                    selectedDay.toString() + "/" + (selectedMonth + 1) + "/" + selectedYear
                try {
                    date = SimpleDateFormat("d/M/yyyy").parse(date)?.let {
                        SimpleDateFormat("yyyy-MM-dd").format(
                            it
                        )
                    }
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                datePickerListener.setDate(date)
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = calendar.timeInMillis
        datePickerDialog.show()
    }

    interface DatePickerListener {
        fun setDate(dateStr: String?)
    }

    /**
     * It is used for check the device's internet connectivity
     */
    fun isNetworkConnected(context: Context): Boolean {
        val result: Boolean
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork ?: return false
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

    /**
     *  This function used to show waiting spinner
     *
     * @param context activity or fragment context
     */
    fun showError(context: Context, msg: String) {
        val dialog = Dialog(context)
        val binding: DialogMessagesBinding =
            DialogMessagesBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.setCancelable(true)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        binding.textMessage.text = msg
        binding.OkMessage.setOnClickListener {
            dialog.dismiss()
        }

        //now that the dialog is set up, it's time to show it
        dialog.show()
    }

}