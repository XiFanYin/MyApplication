package com.github.rxcamera.myapplication.camera

import android.app.Activity
import android.widget.FrameLayout
import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.perview.PreviewImpl

interface CameraImpl {

    /**
     * 打开相机
     */
    fun openCamera(context: Activity, config: Config, previewImpl: PreviewImpl)


}
