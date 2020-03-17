package com.shanemaglangit.detector.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shanemaglangit.detector.R
import com.shanemaglangit.detector.databinding.FragmentCountdownBinding

class CountdownFragment : Fragment() {
    private lateinit var binding: FragmentCountdownBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountdownBinding.inflate(inflater, container, false)

        return binding.root
    }

}
