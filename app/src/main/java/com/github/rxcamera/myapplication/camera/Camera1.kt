package com.github.rxcamera.myapplication.camera

import CameraUtils.CameraUtil
import android.app.Activity
import android.hardware.Camera
import android.widget.FrameLayout
import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.error.NoCameraError
import com.github.rxcamera.myapplication.error.OpenCameraError
import com.github.rxcamera.myapplication.event.OpenCameraEvent
import com.github.rxcamera.myapplication.perview.PreviewImpl
import io.reactivex.Observable


class Camera1 : CameraImpl {
    //相机实力
    var mCamera: Camera? = null

    override fun openCamera(context: Activity, config: Config, previewImpl: PreviewImpl) {

        Observable.create<OpenCameraEvent> {
            if (!CameraUtil.checkCameraHardware(context)) {
                it.onError(NoCameraError("设备没有相机"))
            } else {
                if (getCameraInstance(config.currentCameraId) == null) {
                    it.onError(OpenCameraError("打开相机失败"))
                } else {

                }
            }
        }

    }


    /** 获取一个相机实例 */
    private fun getCameraInstance(cameraId: Int): Camera? {
        return try {
            if (mCamera != null) {
                releaseCamera()//释放相机
            }
            Camera.open(cameraId)
        } catch (e: Exception) {
            // 相机是不可用的（在使用中或不存在）
            null
        }
    }

    /*释放相机，回调相机关闭*/
    private fun releaseCamera() {
        if (mCamera != null) {
            mCamera!!.release()
            mCamera = null
        }
    }
}
