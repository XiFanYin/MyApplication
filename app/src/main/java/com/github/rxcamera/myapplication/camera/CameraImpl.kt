package com.github.rxcamera.myapplication.camera

import android.graphics.Bitmap
import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.manager.CameraManager

interface CameraImpl {

    /**
     * 打开相机
     */
    fun openCamera(manager: CameraManager, config: Config)


}
