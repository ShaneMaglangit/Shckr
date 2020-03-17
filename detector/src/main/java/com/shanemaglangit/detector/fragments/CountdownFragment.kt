package com.shanemaglangit.detector.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shanemaglangit.detector.R
import com.shanemaglangit.detector.databinding.FragmentCountdownBinding

class CountdownFragment : Fragment() {
    private lateinit var binding: FragmentCountdownBinding
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountdownBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        countDownTimer = object: CountDownTimer(5000, 1000) {
            override fun onFinish() {
                val result = (1 until 4).random()
                if(result == 1) findNavController().navigate(R.id.action_countdownFragment_to_lieFragment)
                else findNavController().navigate(R.id.action_countdownFragment_to_truthFragment)
            }

            override fun onTick(millisUntilFinished: Long) {
                binding.textCountdown.text = (millisUntilFinished / 1000).toString()
            }
        }.start()
    }

    override fun onPause() {
        super.onPause()
        countDownTimer.cancel()
    }

}
