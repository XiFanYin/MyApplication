package com.github.rxcamera.myapplication.manager

import CameraUtils.CameraUtil
import android.content.Context
import android.os.Build
import android.widget.Toast
import com.github.rxcamera.myapplication.camera.Camera1
import com.github.rxcamera.myapplication.camera.Camera2
import com.github.rxcamera.myapplication.camera.CameraImpl
import com.github.rxcamera.myapplication.config.Config

class CameraManager(val context: Context) {
    //相机实现接口
    private var cameraImpl: CameraImpl
    //相机默认配置
    var config: Config

    init {
        //初始化相机接口
        cameraImpl = if (Build.VERSION.SDK_INT < 21) Camera1() else Camera2()
        //打开相机默认配置
        config = Config.Builder
                .useBackCamera()//默认使用后置摄像头
                .build()
    }

    /**
     * 改变相机默认配置
     */
    fun config(config2: Config): CameraManager {
        config = config2
        return this
    }


    /**
     * 打开相机
     */
    fun openCamera() {
        cameraImpl.openCamera(this, config)
    }


    /**
     * 相机不可用时候调用
     */
    fun CameraNoAvailable() {

    }


}
