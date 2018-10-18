package com.github.rxcamera.myapplication.camera

import android.hardware.Camera
import android.view.SurfaceHolder
import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.perview.PreviewImpl
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.lang.Exception

/*使用Camera1的API*/
class Camera1(val preview: PreviewImpl) : CameraImpl, SurfaceHolder.Callback {
    /*相机对象*/
    lateinit var mCamera: Camera
    /*相机预览数据原始流*/
    lateinit var publishSubject: PublishSubject<ByteArray>
    /*相机整体对外的流*/
    var totlePublishSubject: PublishSubject<ByteArray> = PublishSubject.create<ByteArray>()
    /*管理流切换*/
    var mCompositeDisposable: CompositeDisposable? = null

    /*打开相机*/
    override fun openCamera(config: Config): Observable<ByteArray> {
        /*初始化流*/
        publishSubject = PublishSubject.create<ByteArray>()
        try {
            /*打开相机*/
            mCamera = Camera.open(config.currentCameraId)
            /*设置旋转方向*/
            mCamera.setDisplayOrientation(90)
            /*添加holder创建回调*/
            preview.getHolder().addCallback(this)
            /*设置预览回调*/
            mCamera.setPreviewCallback { bytes, camera -> publishSubject.onNext(bytes) }
            /*订阅预览流，发送到对应外部的流中*/
            publishSubject.subscribe({ totlePublishSubject.onNext(it) }, {}, {}, { addDisposable(it) })
            /*切换摄像头打开预览的代码*/
            mCamera.setPreviewDisplay(preview.getHolder())
            mCamera.startPreview()

        } catch (e: Exception) {

        }

        return totlePublishSubject
    }


    /*切换摄像头*/
    override fun switchCamera(config: Config) {
        if (mCamera == null) {
            return
        }
        publishSubject.onComplete()
        unDisposable()
        mCamera.stopPreview()
        mCamera.setPreviewCallback(null)
        mCamera.release()

        openCamera(config)
    }


    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        mCamera.setPreviewDisplay(preview.getHolder())
        mCamera.startPreview()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {

    }

    override fun surfaceCreated(holder: SurfaceHolder?) {

    }


    fun addDisposable(subscription: Disposable) {
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(subscription)
    }


    fun unDisposable() {
        //这里不仅切断了流，而且也取消了网络请求
        mCompositeDisposable?.dispose()
        mCompositeDisposable?.clear()
        mCompositeDisposable = null

    }

}


