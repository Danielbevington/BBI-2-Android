package com.bbi.bizbulls.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bbi.bizbulls.R
import com.bbi.bizbulls.data.adaptermodel.SliderItem
import com.bbi.bizbulls.databinding.FragmentHomeBinding
import com.bbi.bizbulls.ProjectInfoActivity
import com.bbi.bizbulls.ui.adapter.*

class HomeCustomerFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
   // var foSliderAdapter: FOSliderAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        sliderUpdate()
        loadServices()
        loadRetailView()
        loadFranchiseView()
        loadFoodView()
        binding.tvRegistration.setOnClickListener {
            val i = Intent(activity, ProjectInfoActivity::class.java)
            startActivity(i)
        }
        return binding.root
    }

    private fun loadServices() {
        binding.rvServices.setHasFixedSize(true)
        binding.rvServices.layoutManager = GridLayoutManager(context, 4)
        val servicesAdapter = activity?.let { ServicesAdapter(it) }
        binding.rvServices.adapter = servicesAdapter
    }

    private fun loadRetailView() {
        binding.rvRental.setHasFixedSize(true)
        binding.rvRental.layoutManager = GridLayoutManager(context, 4)
        val retailAndHouseholdAdapter = activity?.let { RetailAndHouseholdAdapter(it) }
        binding.rvRental.adapter = retailAndHouseholdAdapter
    }

    private fun loadFranchiseView() {
        binding.rvFranchise.setHasFixedSize(true)
        binding.rvFranchise.layoutManager = GridLayoutManager(context, 4)
        val franchiseeAdapter = activity?.let { FranchiseeAdapter(it) }
        binding.rvFranchise.adapter = franchiseeAdapter
    }

    private fun loadFoodView() {
        binding.recyclerleads.setHasFixedSize(true)
        binding.recyclerleads.layoutManager = GridLayoutManager(context, 4)
        val foodAndBeverageAdapter = activity?.let { FoodAndBeverageAdapter(it) }
        binding.recyclerleads.adapter = foodAndBeverageAdapter
//        foodAndBeverageAdapter.setClickListener { view, position ->
//            val i = Intent(activity, ProjectInfoActivity::class.java)
//            startActivityForResult(i, 99)
//        }
    }

    fun sliderUpdate() {
        // for Demo Purpose
        val sliderItemList: MutableList<SliderItem> = ArrayList()
        sliderItemList.add(
            SliderItem(
                "fd",
                "https://images.pexels.com/photos/9868884/pexels-photo-9868884.jpeg?auto=compress&amp;cs=tinysrgb&amp;dpr=3&amp;h=750&amp;w=1260",
                R.drawable.slider
            )
        )
        sliderItemList.add(
            SliderItem(
                "fd",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                R.drawable.slider
            )
        )
        sliderItemList.add(
            SliderItem(
                "fd",
                "https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260",
                R.drawable.slider
            )
        )
//        foSliderAdapter = FOSliderAdapter(activity, sliderItemList)
//        binding!!.imgsliderfo.setSliderAdapter(foSliderAdapter!!)
//        binding!!.imgsliderfo.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//        binding!!.imgsliderfo.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
//        binding!!.imgsliderfo.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
//        binding!!.imgsliderfo.indicatorSelectedColor = Color.WHITE
//        binding!!.imgsliderfo.indicatorUnselectedColor = Color.GRAY
//        binding!!.imgsliderfo.scrollTimeInSec = 3
//        binding!!.imgsliderfo.isAutoCycle = true
//        binding!!.imgsliderfo.startAutoCycle()
    }
}