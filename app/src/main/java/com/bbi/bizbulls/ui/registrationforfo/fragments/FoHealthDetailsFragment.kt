package com.bbi.bizbulls.ui.registrationforfo.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.bbi.bizbulls.R
import com.bbi.bizbulls.databinding.FoFrgHealthDetailsBinding
import com.bbi.bizbulls.ui.registrationforfo.FoRegistrationDashBoardActivity
import com.bbi.bizbulls.ui.registrationforfo.FranchiseeRegistrationVIewModel
import com.google.gson.JsonObject

class FoHealthDetailsFragment : Fragment() {
    private lateinit var binding: FoFrgHealthDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FoFrgHealthDetailsBinding.inflate(inflater, container, false)

        loadAllSpinners()
        binding.stepSubmit.setOnClickListener {
            sendHealthDetail()
        }

        return binding.root
    }

    private fun sendHealthDetail() {
        val jsonObject = JsonObject()
        jsonObject.addProperty(
            "birthidentificationmarks",
            binding.edtbirthmarkesone.text.toString().trim()
        )
        jsonObject.addProperty(
            "birthidentificationmarks2",
            binding.edtbirthmarketwo.text.toString().trim()
        )
        jsonObject.addProperty("handuse", binding.spnrhanduse.selectedItem.toString().trim())
        jsonObject.addProperty("height", binding.edtheight.text.toString().trim())
        jsonObject.addProperty("weight", binding.edtweight.text.toString().trim())
        jsonObject.addProperty("bloodgroup", binding.spnrbloodgroup.selectedItem.toString().trim())
        jsonObject.addProperty("willingtodonate", binding.edtwillingdonate.text.toString().trim())
        jsonObject.addProperty("physycalhandicape", binding.edttypeofph.text.toString().trim())
        jsonObject.addProperty("typeofph", binding.spnrhandicapped.selectedItem.toString().trim())
        jsonObject.addProperty(
            "surgelesstreatmentundergo",
            binding.spnrsurgeries.selectedItem.toString()
        )
        jsonObject.addProperty("typeofsurgery", binding.edttypeofsurgery.text.toString().trim())
        jsonObject.addProperty(
            "anyotherhealthissue",
            binding.spnrhealthissued.selectedItem.toString()
        )
        jsonObject.addProperty(
            "otherissuesdetail",
            binding.edttypeofhealthissue.text.toString().trim()
        )
        jsonObject.addProperty("anyunhealthyhabit", binding.spnrhabbit.selectedItem.toString())
        jsonObject.addProperty("habbitdetails", binding.edttypeofhabbits.text.toString().trim())

        // Call remote Api service to save the Health Details
          FranchiseeRegistrationVIewModel().sendHealthDetail(requireActivity(), jsonObject)
    }

    private fun loadAllSpinners() {
        val handUseAdapter: ArrayAdapter<*> =
            ArrayAdapter(
                requireActivity(),
                R.layout.spinner_corridor_selected,
                resources.getStringArray(R.array.handUsed)
            )
        handUseAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spnrhanduse.adapter = handUseAdapter
        binding.spnrhanduse.setSelection(0)

        val bloodGroupAdapter: ArrayAdapter<*> =
            ArrayAdapter(
                requireActivity(),
                R.layout.spinner_corridor_selected,
                resources.getStringArray(R.array.bloodGroup)
            )
        bloodGroupAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spnrbloodgroup.adapter = bloodGroupAdapter
        binding.spnrbloodgroup.setSelection(0)

        val handicappedAdapter =
            ArrayAdapter(
                requireActivity(),
                R.layout.spinner_corridor_selected,
                resources.getStringArray(R.array.YesNo)
            )
        handicappedAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spnrhandicapped.adapter = handicappedAdapter
        binding.spnrhandicapped.setSelection(0)

        val surgeriesAdapter: ArrayAdapter<*> =
            ArrayAdapter(
                requireActivity(),
                R.layout.spinner_corridor_selected,
                resources.getStringArray(R.array.YesNo)
            )
        surgeriesAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spnrsurgeries.adapter = surgeriesAdapter
        binding.spnrsurgeries.setSelection(0)

        val healthIssuedAdapter: ArrayAdapter<String> =
            ArrayAdapter(
                requireActivity(),
                R.layout.spinner_corridor_selected,
                resources.getStringArray(R.array.YesNo)
            )
        healthIssuedAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spnrhealthissued.adapter = healthIssuedAdapter
        binding.spnrhealthissued.setSelection(0)

        val habitAdapter: ArrayAdapter<*> =
            ArrayAdapter(
                requireActivity(),
                R.layout.spinner_corridor_selected,
                resources.getStringArray(R.array.YesNo)
            )
        habitAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spnrhabbit.adapter = habitAdapter
        binding.spnrhabbit.setSelection(0)
    }

}