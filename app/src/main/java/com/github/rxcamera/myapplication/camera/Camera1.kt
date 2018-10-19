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
    /*相机预览数据原始流*/
    lateinit var publishSubject: PublishSubject<ByteArray>
    /*相机整体对外的流*/
    var totlePublishSubject: PublishSubject<BaseEvent> = PublishSubject.create<BaseEvent>()
    /*管理流切换*/
    var mCompositeDisposable: CompositeDisposable? = null

    /*打开相机*/
    override fun openCamera(config: Config): Observable<BaseEvent> {
        /*初始化流*/
        publishSubject = PublishSubject.create<ByteArray>()
        try {
            /*打开相机*/
            mCamera = Camera.open(config.currentCameraId)
            /*设置旋转方向*/
            mCamera?.setDisplayOrientation(90)
            /*添加holder创建回调*/
            preview.getHolder().addCallback(this)
            /*设置预览回调*/
            mCamera?.setPreviewCallback { bytes, camera -> publishSubject.onNext(bytes) }
            /*订阅预览流，发送到对应外部的流中*/
            publishSubject.subscribe({ totlePublishSubject.onNext(BaseEvent(it, EventType.PERVIEW)) })
            /*切换摄像头打开预览的代码*/
            mCamera?.setPreviewDisplay(preview.getHolder())
            mCamera?.startPreview()

        } catch (e: Exception) {

        }

        return totlePublishSubject
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

    override fun switchFlash() {

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


