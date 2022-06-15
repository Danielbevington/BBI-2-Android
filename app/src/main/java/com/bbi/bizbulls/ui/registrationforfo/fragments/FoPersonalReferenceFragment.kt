package com.bbi.bizbulls.ui.registrationforfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bbi.bizbulls.databinding.FoFrgPersonalReferencesBinding

class FoPersonalReferenceFragment : Fragment() {
    private lateinit var binding: FoFrgPersonalReferencesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FoFrgPersonalReferencesBinding.inflate(inflater, container, false)




        return binding.root
    }
}