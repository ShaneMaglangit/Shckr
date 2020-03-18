package com.shanemaglangit.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shanemaglangit.instruction.databinding.FragmentStepBinding

class StepFragment(val step: String) : Fragment() {
    private lateinit var binding: FragmentStepBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStepBinding.inflate(layoutInflater, container, false)
        binding.textStep.text = step
        return binding.root
    }

}
