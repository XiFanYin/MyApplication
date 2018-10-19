package com.github.rxcamera.myapplication.camera

import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.event.BaseEvent
import io.reactivex.Observable

interface CameraImpl {

    /**
     * 打开相机
     */
    fun openCamera(config: Config): Observable<BaseEvent>

    /*释放相机*/
    fun releaseCamera()


    /*选择摄像头*/
    fun switchCamera(config: Config)

    /*选择散光灯*/
    fun switchFlash()


}
