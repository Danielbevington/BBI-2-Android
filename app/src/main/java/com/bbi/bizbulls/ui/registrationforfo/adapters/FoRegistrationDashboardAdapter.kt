package com.bbi.bizbulls.ui.registrationforfo.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bbi.bizbulls.R
import com.bbi.bizbulls.databinding.FoAdapterDashboardBinding
import com.bbi.bizbulls.ui.registrationforfo.interfaces.IFoRegistrationStepsClickListener

/**
 * Created by Daniel.
 */
class FoRegistrationDashboardAdapter(
    private val mContext: Context,
    private val stepsClickListener: IFoRegistrationStepsClickListener
) : RecyclerView.Adapter<FoRegistrationDashboardAdapter.FoRegistrationDashboardViewHolder>() {
    private var itemNames =  mContext.resources.getStringArray(R.array.FoRegistrationStepsNames)
   // private var itemIcons = mContext.resources.getIntArray(R.array.FoRegistrationStepsIons)
    private var itemIcons = intArrayOf(
        R.drawable.delivery,
        R.drawable.g_services,
        R.drawable.cab,
        R.drawable.maintaince,
        R.drawable.property_broker,
        R.drawable.property_broker,
        R.drawable.property_broker,
        R.drawable.property_broker,
        R.drawable.property_broker,
        R.drawable.property_broker,
        R.drawable.property_broker,
        R.drawable.schedule
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoRegistrationDashboardViewHolder {
        val itemBinding = FoAdapterDashboardBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return FoRegistrationDashboardViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = itemNames.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FoRegistrationDashboardViewHolder, position: Int) {
        val name = itemNames[position]
        val icon = itemIcons[position]
        holder.itemBinding.itemName.text = name
        holder.itemBinding.itemIcon.setImageResource(icon)

//        Picasso.get().load(model.avatar).into(holder.itemBinding.profilePic)

        holder.itemView.setOnClickListener {
            stepsClickListener.onStepsClickListener(itemNames[position].toString(), position)
        }
    }


    /*
    * Used for binding Recyclerview
    *
    * @param itemBinding binding the Users UI elements
    * */
    class FoRegistrationDashboardViewHolder(val itemBinding: FoAdapterDashboardBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}