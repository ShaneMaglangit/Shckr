package com.shanemaglangit.detector.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shanemaglangit.detector.R
import com.shanemaglangit.detector.databinding.FragmentTruthBinding

class TruthFragment : Fragment() {
    private lateinit var binding: FragmentTruthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTruthBinding.inflate(layoutInflater, container, false)
        binding.buttonReplay.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }
}
