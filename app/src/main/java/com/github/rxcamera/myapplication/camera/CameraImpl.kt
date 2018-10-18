package com.github.rxcamera.myapplication.camera

import android.graphics.Bitmap
import com.github.rxcamera.myapplication.config.Config
import io.reactivex.Observable

interface CameraImpl {

    /**
     * 打开相机
     */
    fun openCamera(config: Config): Observable<ByteArray>

    fun switchCamera(config: Config)


}
