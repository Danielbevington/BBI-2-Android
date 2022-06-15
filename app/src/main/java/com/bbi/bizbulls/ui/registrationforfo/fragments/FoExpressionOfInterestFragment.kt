package com.bbi.bizbulls.ui.registrationforfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bbi.bizbulls.databinding.FoFrgExpressionOfInterestBinding

class FoExpressionOfInterestFragment : Fragment() {
    private lateinit var binding: FoFrgExpressionOfInterestBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FoFrgExpressionOfInterestBinding.inflate(inflater, container, false)




        return binding.root
    }
}