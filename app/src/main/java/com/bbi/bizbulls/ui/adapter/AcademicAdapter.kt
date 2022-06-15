package com.bbi.bizbulls.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bbi.bizbulls.R
import com.bbi.bizbulls.data.adaptermodel.Academic

class AcademicAdapter(var context: Context, academic_List: List<Academic>) :
    RecyclerView.Adapter<AcademicAdapter.viewHolder>() {
    var academicList: List<Academic> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_academic, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val aceAcademic = academicList[position]
        holder.textinput_counter.text = (position + 1).toString()
        holder.txtqulaitification.text = aceAcademic.qualification
        holder.txtyearofpassing.text = aceAcademic.year
        holder.txtpercentageexam.text = aceAcademic.percentage + "%"
        holder.txtinsitutename.text = aceAcademic.institutename
    }

    override fun getItemCount(): Int {
        return academicList.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textinput_counter: AppCompatTextView
        var txtqulaitification: AppCompatTextView
        var txtyearofpassing: AppCompatTextView
        var txtpercentageexam: AppCompatTextView
        var txtinsitutename: AppCompatTextView

        init {
            textinput_counter = itemView.findViewById(R.id.textinput_counter)
            txtqulaitification = itemView.findViewById(R.id.txtqulaitification)
            txtyearofpassing = itemView.findViewById(R.id.txtyearofpassing)
            txtpercentageexam = itemView.findViewById(R.id.txtpercentageexam)
            txtinsitutename = itemView.findViewById(R.id.txtinsitutename)
        }
    }

    init {
        academicList = academic_List
    }
}