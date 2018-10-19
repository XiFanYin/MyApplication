package com.github.rxcamera.myapplication.cameraview

import com.github.rxcamera.RxCameraView
import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.event.BaseEvent
import io.reactivex.Observable


interface CameraViewImpl {

    /*打开相机*/
    fun openCamera(config: Config=Config.Builder.useBackCamera().build()): Observable<BaseEvent>

    /*关闭相机*/
    fun releaseCamera()

    /*选择相机*/
    fun switchCamera()

    /*是否开启闪光灯*/
    fun switchFlash()


}
