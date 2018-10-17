package com.github.rxcamera

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.github.rxcamera.myapplication.camera.Camera1
import com.github.rxcamera.myapplication.camera.CameraImpl
import com.github.rxcamera.myapplication.cameraview.CameraViewImpl
import com.github.rxcamera.myapplication.perview.PreviewImpl
import com.github.rxcamera.myapplication.perview.SurfaceViewPreview
import io.reactivex.Observable

/*相机自定义控件，对外提供rx方式的统一操作符*/
class RxCameraView : FrameLayout, CameraViewImpl {


    var cameraImpl: CameraImpl
    var preview: PreviewImpl

    /*构造方法*/
    constructor(context: Context) : this(context, null)

    /*构造方法*/
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    /*构造方法*/
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        /*创建预览接口，把当前自定义控件传递进去，好给里边打不同的预览控件*/
        preview = SurfaceViewPreview(context, this)
        /*相机核心类*/
        cameraImpl = Camera1(preview)

    }

    /**
     * 打开相机
     */
    override fun openCamera(): Observable<RxCameraView> {
        return Observable.create {
            if (cameraImpl.openCamera()) {
                it.onNext(this)
            } else {
                it.onError(RuntimeException("打开相机失败"))
            }
        }
    }


    override fun closeCamera() {

    }

    override fun switchFlash() {

    }

    override fun switchCamera() {

    }


}