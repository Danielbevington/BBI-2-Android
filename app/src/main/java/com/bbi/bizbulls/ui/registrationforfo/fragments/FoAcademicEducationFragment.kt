package com.bbi.bizbulls.ui.registrationforfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bbi.bizbulls.databinding.FoFrgAcademicEducationDetailsBinding
import com.bbi.bizbulls.databinding.FoFrgChecklistUnderstandingBinding

class FoAcademicEducationFragment : Fragment() {
    private lateinit var binding: FoFrgAcademicEducationDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FoFrgAcademicEducationDetailsBinding.inflate(inflater, container, false)




        return binding.root
    }
}