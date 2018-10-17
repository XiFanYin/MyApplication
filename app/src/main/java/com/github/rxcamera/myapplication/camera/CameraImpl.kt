package com.github.rxcamera.myapplication.camera

interface CameraImpl {

    /**
     * 打开相机
     */
    fun openCamera(a :Int):Boolean

    fun switchCamera(cameraId: Int)


}
