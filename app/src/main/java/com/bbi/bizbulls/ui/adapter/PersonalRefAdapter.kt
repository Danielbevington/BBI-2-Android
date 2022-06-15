package com.bbi.bizbulls.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bbi.bizbulls.R
import com.bbi.bizbulls.data.adaptermodel.Personalref

class PersonalRefAdapter(var context: Context, personalref_List: List<Personalref>) :
    RecyclerView.Adapter<PersonalRefAdapter.viewHolder>() {
    var personalref: List<Personalref> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_personalref, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val f = personalref[position]
        holder.textinput_counter.text = (position + 1).toString()
        holder.txtname.text = f.name
        holder.txtrelation.text = f.relation
        holder.txtage.text = f.age + " years old"
        holder.txtlocation.text = f.location
        holder.txtoccupation.text = f.occupation
        holder.txtcontactnumber.text = f.contactnumber
    }

    override fun getItemCount(): Int {
        return personalref.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textinput_counter: AppCompatTextView
        var txtname: AppCompatTextView
        var txtrelation: AppCompatTextView
        var txtage: AppCompatTextView
        var txtlocation: AppCompatTextView
        var txtoccupation: AppCompatTextView
        var txtcontactnumber: AppCompatTextView

        init {
            textinput_counter = itemView.findViewById(R.id.textinput_counter)
            txtname = itemView.findViewById(R.id.txtname)
            txtrelation = itemView.findViewById(R.id.txtrelation)
            txtage = itemView.findViewById(R.id.txtage)
            txtlocation = itemView.findViewById(R.id.txtlocation)
            txtoccupation = itemView.findViewById(R.id.txtoccupation)
            txtcontactnumber = itemView.findViewById(R.id.txtcontactnumber)
        }
    }

    init {
        personalref = personalref_List
    }
}