package com.github.rxcamera.myapplication.cameraview

import com.github.rxcamera.RxCameraView
import io.reactivex.Observable


interface CameraViewImpl {

    /*打开相机*/
    fun openCamera()

    /*关闭相机*/
    fun closeCamera()

    /*选择相机*/
    fun switchCamera()

    /*是否开启闪光灯*/
    fun switchFlash()


}
