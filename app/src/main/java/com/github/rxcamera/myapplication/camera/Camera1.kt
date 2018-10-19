package com.github.rxcamera.myapplication.camera

import android.hardware.Camera
import android.view.SurfaceHolder
import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.event.BaseEvent
import com.github.rxcamera.myapplication.event.EventType
import com.github.rxcamera.myapplication.perview.PreviewImpl
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.lang.Exception

/*使用Camera1的API*/
class Camera1(val preview: PreviewImpl) : CameraImpl, SurfaceHolder.Callback {

    /*相机对象*/
    var mCamera: Camera? = null
    /*相机服务类对象*/
    var parameters: Camera.Parameters? = null


    /*相机整体对外的流*/
    var totlePublishSubject: PublishSubject<BaseEvent> = PublishSubject.create<BaseEvent>()

    /*打开相机*/
    override fun openCamera(config: Config): Observable<BaseEvent> {

        try {
            /*打开相机*/
            mCamera = Camera.open(config.currentCameraId)
            parameters = mCamera?.parameters
            if (configFlash(config)) {
                totlePublishSubject.onNext(BaseEvent(null, EventType.FLASH_SUCCEE))
            } else {
                totlePublishSubject.onNext(BaseEvent(null, EventType.FLASH_FAILED))
            }


            /*设置旋转方向*/
            mCamera?.setDisplayOrientation(90)
            /*添加holder创建回调*/
            preview.getHolder().addCallback(this)
            /*设置预览回调*/
            mCamera?.setPreviewCallback { bytes, camera ->

                totlePublishSubject.onNext(BaseEvent(bytes, EventType.PERVIEW))
            }
            /*切换摄像头打开预览的代码*/
            mCamera?.setPreviewDisplay(preview.getHolder())
            mCamera?.startPreview()

        } catch (e: Exception) {

        }




        return totlePublishSubject
    }

    /*配置散光灯*/
    fun configFlash(config: Config): Boolean {
        /*获取硬件支持的闪光灯模式*/
        val modes = mCamera?.parameters?.supportedFlashModes
        /*获取用户设置的模式*/
        val mode = config.flashModel.Model
        if (modes != null && modes!!.contains(mode)) {
            /*如果包含这种模式*/
            parameters?.flashMode = mode
            return true
        } else {
            /*如果不包含这种模式*/
            return false
        }
    }


    /*切换摄像头*/
    override fun switchCamera(config: Config) {
        /*如果相机不打开不能切换摄像头*/
        if (mCamera == null) {
            return
        }
        releaseCamera()
        openCamera(config)
    }

    /*释放相机资源*/
    override fun releaseCamera() {
        /*停止预览*/
        mCamera?.stopPreview()
        /*预览为null*/
        mCamera?.setPreviewCallback(null)
        /*释放相机*/
        mCamera?.release()

    }


    /*拍照*/
    override fun takePicture() {


    }


    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        mCamera!!.setPreviewDisplay(preview.getHolder())
        mCamera!!.startPreview()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {

    }

    override fun surfaceCreated(holder: SurfaceHolder?) {

    }


}


