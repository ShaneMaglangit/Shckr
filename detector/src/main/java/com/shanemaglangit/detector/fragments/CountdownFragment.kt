package com.shanemaglangit.detector.fragments

import android.app.AlertDialog
import android.content.Context
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shanemaglangit.detector.R
import com.shanemaglangit.detector.databinding.FragmentCountdownBinding
import kotlin.random.Random

class CountdownFragment : Fragment() {
    private lateinit var binding: FragmentCountdownBinding
    private lateinit var countDownTimer: CountDownTimer
    private var cameraManager: CameraManager? = null
    private var camera: Camera? = null
    private var backCameraId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountdownBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val result = Random.nextFloat()
        countDownTimer = object: CountDownTimer(6000, 1000) {
            override fun onFinish() {
                if(result <= 0.45) findNavController().navigate(R.id.action_countdownFragment_to_lieFragment)
                else findNavController().navigate(R.id.action_countdownFragment_to_truthFragment)
                camera?.release()
            }

            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                binding.textCountdown.text = secondsLeft.toString()
                if(secondsLeft.toInt() == 3 && result <= 0.45) flash()
            }
        }.start()
    }

    private fun flash() {
        if (Build.VERSION.SDK_INT >= 23) {
            cameraManager = context!!.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            cameraManager!!.cameraIdList.forEach {
                if(cameraManager!!.getCameraCharacteristics(it).get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_BACK) {
                    backCameraId = it
                }
            }
            cameraManager!!.setTorchMode(backCameraId, true)
        } else {
            camera = Camera.open()

            val surfaceTexture = SurfaceTexture(10)
            val parameters = camera!!.parameters

            if(parameters.supportedFlashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                parameters.flashMode = Camera.Parameters.FLASH_MODE_TORCH
            } else if(parameters.supportedFlashModes.contains(Camera.Parameters.FLASH_MODE_ON)) {
                parameters.flashMode = Camera.Parameters.FLASH_MODE_ON
            }

            camera!!.enableShutterSound(false)
            camera!!.setPreviewTexture(surfaceTexture)
            camera!!.parameters = parameters
            camera!!.startPreview()
        }
    }

    override fun onPause() {
        super.onPause()
        countDownTimer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT < 23) camera?.release()
        else cameraManager?.setTorchMode(backCameraId, false)
    }

}
