package com.bbi.bizbulls.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bbi.bizbulls.R
import com.bbi.bizbulls.data.adaptermodel.Family

class FamilyAdapter(var context: Context, family_List: List<Family>) :
    RecyclerView.Adapter<FamilyAdapter.viewHolder>() {
    var family: List<Family> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_family, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val f = family[position]
        holder.textinput_counter.text = (position + 1).toString()
        holder.txtname.text = f.name
        holder.txtrelation.text = f.relation
        holder.txtage.text = f.age + " years old"
        holder.txteducation.text = f.education
        holder.txtoccupation.text = f.occupation
        holder.txtcontactnumber.text = f.contactnumber
    }

    override fun getItemCount(): Int {
        return family.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textinput_counter: AppCompatTextView
        var txtname: AppCompatTextView
        var txtrelation: AppCompatTextView
        var txtage: AppCompatTextView
        var txteducation: AppCompatTextView
        var txtoccupation: AppCompatTextView
        var txtcontactnumber: AppCompatTextView

        init {
            textinput_counter = itemView.findViewById(R.id.textinput_counter)
            txtname = itemView.findViewById(R.id.txtname)
            txtrelation = itemView.findViewById(R.id.txtrelation)
            txtage = itemView.findViewById(R.id.txtage)
            txteducation = itemView.findViewById(R.id.txteducation)
            txtoccupation = itemView.findViewById(R.id.txtoccupation)
            txtcontactnumber = itemView.findViewById(R.id.txtcontactnumber)
        }
    }

    init {
        family = family_List
    }
}