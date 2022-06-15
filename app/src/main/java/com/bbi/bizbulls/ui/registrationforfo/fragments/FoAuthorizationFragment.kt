package com.bbi.bizbulls.ui.registrationforfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bbi.bizbulls.databinding.FoFrgAuthorizationBinding

class FoAuthorizationFragment : Fragment() {
    private lateinit var binding: FoFrgAuthorizationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FoFrgAuthorizationBinding.inflate(inflater, container, false)




        return binding.root
    }
}