package com.bbi.bizbulls.ui.registrationforfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bbi.bizbulls.databinding.FoFrgSocialIdentityDetailsBinding

class FoSocialIdentityFragment : Fragment() {
    private lateinit var binding: FoFrgSocialIdentityDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FoFrgSocialIdentityDetailsBinding.inflate(inflater, container, false)




        return binding.root
    }
}