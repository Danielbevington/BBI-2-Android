package com.bbi.bizbulls.ui.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bbi.bizbulls.utils.Globals
import com.bbi.bizbulls.R
import com.bbi.bizbulls.databinding.ItemSeeAllBinding
import com.bbi.bizbulls.model.HomeMenuListModel

class FoodAndBeverageAdapter(activity: FragmentActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var context: Context
    var list = ArrayList<HomeMenuListModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Globals.Item_Type) {
            val view = LayoutInflater.from(context).inflate(R.layout.itemlist, parent, false)
            MyViewHolder(view)
        } else {
            val binding = ItemSeeAllBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            SeeAllHolder(binding)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == Globals.Item_Type) {
            val holder = viewHolder as MyViewHolder
            holder.textView.text = list[position].title
            val drawable = ContextCompat.getDrawable(context, list[position].icon)
            holder.imageView.setImageDrawable(drawable)
            holder.textView.setTypeface(null, Typeface.NORMAL)
            holder.itemView.setOnClickListener {
            }
        } else {
            val holder = viewHolder as SeeAllHolder
            holder.binding.itemLayout.setOnClickListener { view: View? -> }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].itemType
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView: TextView

        init {
            imageView = itemView.findViewById(R.id.iv_top_small_card)
            textView = itemView.findViewById(R.id.tv_top_small_card)
        }
    }

    init {
        context = activity
        list.add(HomeMenuListModel("Beauty & Health", R.drawable.uncle_kitchen))
        list.add(HomeMenuListModel("Food & Beverage", R.drawable.icy_doves))
        list.add(HomeMenuListModel("Education", R.drawable.freaky_bakes))
        list.add(HomeMenuListModel("Retail and Household", R.drawable.shahi_shagun))
        list.add(HomeMenuListModel("Automotive & Spares", R.drawable.treat_more))
        list.add(HomeMenuListModel("Agri & Organics", R.drawable.shahi_shagun))
        list.add(HomeMenuListModel("Sports & Fitness", R.drawable.treat_more))
        val model = HomeMenuListModel(Globals.SEE_ALL_TAG, R.drawable.ic_leftarrowgrey)
        model.itemType = Globals.See_All_Type
        list.add(model)
    }
}