package com.github.rxcamera.myapplication.manager

import android.app.Activity
import android.os.Build
import android.widget.FrameLayout
import com.github.rxcamera.myapplication.camera.Camera1
import com.github.rxcamera.myapplication.camera.Camera2
import com.github.rxcamera.myapplication.camera.CameraImpl
import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.perview.PreviewImpl
import com.github.rxcamera.myapplication.perview.SurfaceViewPreview
import com.github.rxcamera.myapplication.perview.TextureViewPreview

class CameraManager(val context: Activity,rongqi: FrameLayout =context.findViewById(android.R.id.content) , var config: Config = Config.Builder.useBackCamera().build()) {
    //相机实现接口
    private var cameraImpl: CameraImpl
    //预览实现接口
    private var previewImpl: PreviewImpl


    init {
        //初始化相机接口
        cameraImpl = if (Build.VERSION.SDK_INT < 21) Camera1() else Camera2()
        //初始化预览接口
        previewImpl = if (Build.VERSION.SDK_INT < 14) SurfaceViewPreview(rongqi) else TextureViewPreview(rongqi)

    }


    /**
     * 打开相机
     */
    fun openCamera() {
        cameraImpl.openCamera(context, config, previewImpl)
    }


}
