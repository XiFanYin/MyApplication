package com.github.rxcamera.myapplication.perview

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.github.rxcamera.myapplication.R

class SurfaceViewPreview(context: Context, parent: FrameLayout) : PreviewImpl {

    init {
        /*当前预览控件添加到自定义中*/
        val view = View.inflate(context, R.layout.surface_view, parent)
    }


}
