package com.github.rxcamera.myapplication.camera

import android.hardware.Camera
import android.view.SurfaceHolder
import com.github.rxcamera.myapplication.perview.PreviewImpl
import io.reactivex.Observable
import java.lang.Exception


class Camera1(val preview: PreviewImpl) : CameraImpl, SurfaceHolder.Callback {

    lateinit var mCamera: Camera

    override fun openCamera(cameraId: Int): Observable<ByteArray> {
        return Observable.create<ByteArray> {
            try {
                mCamera = Camera.open(cameraId)
                mCamera.setDisplayOrientation(90)
                preview.getHolder().addCallback(this)
                mCamera.setPreviewCallback { bytes, camera -> it.onNext(bytes) }
                /*切换摄像头用到的代码*/
                mCamera.setPreviewDisplay(preview.getHolder())
                mCamera.startPreview()
            } catch (e: Exception) {

            }

        }
    }


    /*切换摄像头*/
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


