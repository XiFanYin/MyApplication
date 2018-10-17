package com.github.rxcamera.myapplication.camera

import android.hardware.Camera
import android.view.SurfaceHolder
import com.github.rxcamera.myapplication.perview.PreviewImpl
import java.lang.Exception


class Camera1(val preview: PreviewImpl) : CameraImpl, SurfaceHolder.Callback {

    lateinit var mCamera: Camera

    override fun openCamera(a: Int): Boolean {
        try {
            mCamera = Camera.open(a)
            mCamera.setDisplayOrientation(90)
            preview.getHolder().addCallback(this)
            mCamera.setPreviewDisplay(preview.getHolder())
            mCamera.startPreview()
            return true
        } catch (e: Exception) {
            return false
        }
    }


    override fun switchCamera(cameraId: Int) {
        if (mCamera == null) {
            return
        }
        mCamera.stopPreview()
        mCamera.release()
        openCamera(cameraId)
    }


    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        mCamera.setPreviewDisplay(preview.getHolder())
        mCamera.startPreview()


    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {

    }

    override fun surfaceCreated(holder: SurfaceHolder?) {

    }


}
