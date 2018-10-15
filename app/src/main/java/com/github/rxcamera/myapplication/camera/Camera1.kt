package com.github.rxcamera.myapplication.camera

import android.app.Activity
import android.hardware.Camera
import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.perview.PreviewImpl


class Camera1 : CameraImpl {
    //相机实力
    var mCamera: Camera? = null

    override fun openCamera(context: Activity, config: Config, previewImpl: PreviewImpl) {

        mCamera = getCameraInstance(config.currentCameraId)
        previewImpl.setcallback(object:PreviewImpl.Call{
            override fun onCall() {
                mCamera!!.setPreviewDisplay(previewImpl.getSurfaceHolder())
                //将预览显示的顺时针旋转设置为度数
                mCamera!!.setDisplayOrientation(90)
                mCamera!!.startPreview()
            }
        })
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
            return null
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
