package com.bbi.bizbulls.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bbi.bizbulls.R
import com.bbi.bizbulls.utils.CommonUtils

class FranchiseeAdapter(activity: FragmentActivity) :
    RecyclerView.Adapter<FranchiseeAdapter.viewHolder>() {
    var context: Context
    var iconsName = arrayOf(
        "Reliable Projects",
        "Lowest Investment",
        "Technology Enabled",
        "Loaning Support",
        "Continuous Training & Guidance",
        "Project Site   Visits",
        "Pre & Post Launch Support",
        "Licensing & Documentation",
        "Set-up arrangement",
        "Arbitrary Service",
        "Super Support Bot"
    )
    var icons = intArrayOf(
        R.drawable._reliableproject,
        R.drawable._lowinvetment,
        R.drawable._technologyenable,
        R.drawable._loaning,
        R.drawable._conttraining,
        R.drawable._sitevise,
        R.drawable._launchsupport,
        R.drawable._liceinsingdoc,
        R.drawable._setuparrangment,
        R.drawable._arbitrary,
        R.drawable._supersupportbot
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_franchiese, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.textView.text = iconsName[position]
        holder.imageView.setImageResource(icons[position])
        holder.itemView.setOnClickListener { v: View? ->
            CommonUtils.toast(
                context,
                "Please wait!!! FO is not yet registered in your location."
            )
        }
    }

    override fun getItemCount(): Int {
        return iconsName.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView: TextView

        init {
            imageView = itemView.findViewById(R.id.iv_top_small_card)
            textView = itemView.findViewById(R.id.tv_top_small_card)
        }
    }

    init {
        context = activity
    }
}