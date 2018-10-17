package com.github.rxcamera.myapplication.perview

import android.content.Context
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.FrameLayout
import com.github.rxcamera.myapplication.R
import com.github.rxcamera.myapplication.R.layout.surface_view

class SurfaceViewPreview(context: Context, parent: FrameLayout) : PreviewImpl {
    val surface_view: SurfaceView

    init {
        /*当前预览控件添加到自定义中*/
        val view = View.inflate(context, R.layout.surface_view, parent)
        surface_view = view.findViewById(R.id.surface_view)
    }

    override fun getHolder(): SurfaceHolder {
        return surface_view.holder
    }


}
