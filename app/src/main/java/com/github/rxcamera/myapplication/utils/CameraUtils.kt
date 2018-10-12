package CameraUtils

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Camera

object CameraUtil {


    /** 检查设备是否有摄像头*/
    fun checkCameraHardware(context: Context): Boolean {
        return if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) true else false
    }


    /** 检查设备是否有前置摄像头*/
    fun checkFrontCameraHardware(context: Context): Boolean {
        return if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) true else false
    }


    /** 获取手机上相机数量*/
    fun getCameraNumber(): Int = Camera.getNumberOfCameras()


    /**获取前置摄像头的id*/
    fun getFrontCameraId(): Int {
        var frontCameraId = -1
        val cameraInfo = Camera.CameraInfo()
        for (i in 0 until getCameraNumber()) {
            Camera.getCameraInfo(i, cameraInfo)
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                frontCameraId = i
                break
            }
        }
        return frontCameraId
    }

    /**获取后置摄像头的id*/
    fun getBackCameraId(): Int {
        var backCameraId = -1
        val cameraInfo = Camera.CameraInfo()
        for (i in 0 until getCameraNumber()) {
            Camera.getCameraInfo(i, cameraInfo)
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                backCameraId = i
                break
            }
        }
        return backCameraId
    }
}


