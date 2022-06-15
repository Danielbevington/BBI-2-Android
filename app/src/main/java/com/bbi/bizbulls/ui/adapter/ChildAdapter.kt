package com.bbi.bizbulls.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bbi.bizbulls.R
import com.bbi.bizbulls.data.adaptermodel.Children

class ChildAdapter(var context: Context, child_List: List<Children>) :
    RecyclerView.Adapter<ChildAdapter.viewHolder>() {
    var childlist: List<Children> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_child, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val f = childlist[position]
        holder.textinput_counter.text = (position + 1).toString()
        holder.txtname.text = f.name
        holder.txtsex.text = f.sex
        holder.txtage.text = f.age + " years old"
        holder.txtinsitutename.text = f.institutename
        holder.txtboard.text = f.univercity
    }

    override fun getItemCount(): Int {
        return childlist.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textinput_counter: AppCompatTextView
        var txtname: AppCompatTextView
        var txtsex: AppCompatTextView
        var txtage: AppCompatTextView
        var txtinsitutename: AppCompatTextView
        var txtboard: AppCompatTextView

        init {
            textinput_counter = itemView.findViewById(R.id.textinput_counter)
            txtname = itemView.findViewById(R.id.txtname)
            txtsex = itemView.findViewById(R.id.txtsex)
            txtage = itemView.findViewById(R.id.txtage)
            txtinsitutename = itemView.findViewById(R.id.txtinsitutename)
            txtboard = itemView.findViewById(R.id.txtboard)
        }
    }

    init {
        childlist = child_List
    }
}