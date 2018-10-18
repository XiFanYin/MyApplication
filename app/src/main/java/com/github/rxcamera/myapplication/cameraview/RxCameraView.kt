package com.github.rxcamera

import CameraUtils.CameraUtil
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.widget.FrameLayout
import com.github.rxcamera.myapplication.camera.Camera1
import com.github.rxcamera.myapplication.camera.CameraImpl
import com.github.rxcamera.myapplication.cameraview.CameraViewImpl
import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.perview.PreviewImpl
import com.github.rxcamera.myapplication.perview.SurfaceViewPreview
import io.reactivex.Observable

/*相机自定义控件，对外提供rx方式的统一操作符*/
class RxCameraView : FrameLayout, CameraViewImpl {
    /*相机接口*/
    private var cameraImpl: CameraImpl
    /*预览接口*/
    private var preview: PreviewImpl

    private lateinit var defultConfig: Config

    /*构造方法*/
    constructor(context: Context) : this(context, null)

    /*构造方法*/
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    /*构造方法*/
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        /*创建预览接口，把当前自定义控件传递进去，好给里边打不同的预览控件*/
        preview = SurfaceViewPreview(context, this)
        /*相机功能核心类*/
        cameraImpl = Camera1(preview)

    }

    /* 打开相机 */
    override fun openCamera(config: Config): Observable<ByteArray> {
        this.defultConfig = config
        return cameraImpl.openCamera(defultConfig)
    }


    /*切换摄像头*/
    override fun switchCamera() {
        if (defultConfig.currentCameraId == CameraUtil.getFrontCameraId()) {
            defultConfig.currentCameraId = CameraUtil.getBackCameraId()
        } else {
            defultConfig.currentCameraId = CameraUtil.getFrontCameraId()
        }
        cameraImpl.switchCamera(defultConfig)
    }


    /*关闭相机*/
    override fun closeCamera() {

    }

    /*切换散光灯*/
    override fun switchFlash() {

    }
}