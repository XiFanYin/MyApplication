package com.github.rxcamera.myapplication.camera

import android.hardware.Camera
import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.manager.CameraManager

class Camera1 : CameraImpl {
    //相机事例
    var mCamera: Camera? = null

    override fun openCamera(manager: CameraManager, config: Config) {
        mCamera = getCameraInstance(config.currentCameraId)

        if (mCamera != null) {

        } else {
            //相机不可用调用
            manager.CameraNoAvailable()
        }
    }


    /** 获取一个相机实例 */
    fun getCameraInstance(cameraId: Int): Camera? {
        return try {
            Camera.open(cameraId)
        } catch (e: Exception) {
            // 相机是不可用的（在使用中或不存在）
            null
        }
    }
}
