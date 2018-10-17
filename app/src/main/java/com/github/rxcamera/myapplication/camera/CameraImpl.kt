package com.github.rxcamera.myapplication.camera

import io.reactivex.Observable

interface CameraImpl {

    /**
     * 打开相机
     */
    fun openCamera(a :Int): Observable<ByteArray>

    fun switchCamera(cameraId: Int)


}
