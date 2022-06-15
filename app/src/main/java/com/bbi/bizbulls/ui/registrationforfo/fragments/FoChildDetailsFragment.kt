package com.bbi.bizbulls.ui.registrationforfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bbi.bizbulls.databinding.FoFrgChildrenDetailsBinding

class FoChildDetailsFragment : Fragment() {
    private lateinit var binding: FoFrgChildrenDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FoFrgChildrenDetailsBinding.inflate(inflater, container, false)




        return binding.root
    }
}