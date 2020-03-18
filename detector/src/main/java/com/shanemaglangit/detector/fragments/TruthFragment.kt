package com.shanemaglangit.detector.fragments

import android.media.MediaPlayer
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
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTruthBinding.inflate(layoutInflater, container, false)
        mediaPlayer = MediaPlayer.create(context, R.raw.truth)
        binding.buttonReplay.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sound()
    }

    private fun sound() {
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}
