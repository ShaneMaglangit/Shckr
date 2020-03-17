package com.shanemaglangit.detector.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.*
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shanemaglangit.detector.databinding.FragmentLieBinding

class LieFragment : Fragment() {
    private lateinit var binding: FragmentLieBinding
    private lateinit var vibrator: Vibrator
    private lateinit var cameraManager: CameraManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLieBinding.inflate(layoutInflater, container, false)
        vibrator = context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        cameraManager = context!!.getSystemService(Context.CAMERA_SERVICE) as CameraManager

        flash()

        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    1000L,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            vibrator.vibrate(1000L)
        }

        binding.buttonReplay.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    private fun flash() {
        if (Build.VERSION.SDK_INT >= 23) {

        } else {
            val surfaceTexture = SurfaceTexture(10)
            val camera = Camera.open()
            val parameters = camera.parameters

            if(parameters.supportedFlashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                parameters.flashMode = Camera.Parameters.FLASH_MODE_TORCH
            } else if(parameters.supportedFlashModes.contains(Camera.Parameters.FLASH_MODE_ON)) {
                parameters.flashMode = Camera.Parameters.FLASH_MODE_ON
            } else {
                AlertDialog.Builder(context).apply {
                    setCancelable(true)
                    setTitle("Flash unavailable")
                    setMessage("No available flash mode was found within your device")
                }.create()
                camera?.release()
                return
            }

            camera.enableShutterSound(false)
            camera.setPreviewTexture(surfaceTexture)
            camera.parameters = parameters
            camera.startPreview()
            camera.takePicture(null, null, object: Camera.PictureCallback {
                override fun onPictureTaken(data: ByteArray?, camera: Camera?) {
                    camera?.stopPreview()
                    camera?.release()
                }
            })
        }
    }
}
