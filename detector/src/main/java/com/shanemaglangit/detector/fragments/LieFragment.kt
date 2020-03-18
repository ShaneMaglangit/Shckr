package com.shanemaglangit.detector.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.media.MediaPlayer
import android.os.*
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shanemaglangit.detector.R
import com.shanemaglangit.detector.databinding.FragmentLieBinding

class LieFragment : Fragment() {
    private lateinit var binding: FragmentLieBinding
    private lateinit var vibrator: Vibrator
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLieBinding.inflate(layoutInflater, container, false)
        vibrator = context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        mediaPlayer = MediaPlayer.create(context, R.raw.lie)

        binding.buttonReplay.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vibrate()
        sound()
    }

    private fun sound() {
        mediaPlayer.start()
    }

    private fun vibrate() {
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    1000L,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            vibrator.vibrate(2000L)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        vibrator.cancel()
    }
}
