package com.bbi.bizbulls.ui.registrationforfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bbi.bizbulls.databinding.FoFrgBankAccountDetailsBinding

class FoBankAccountFragment : Fragment() {
    private lateinit var binding: FoFrgBankAccountDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FoFrgBankAccountDetailsBinding.inflate(inflater, container, false)




        return binding.root
    }
}