package com.shanemaglangit.detector.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shanemaglangit.detector.R
import com.shanemaglangit.detector.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        binding.imageStart.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_countdownFragment)
        }
        return binding.root
    }
}
