package com.github.rxcamera.myapplication.perview

import android.view.SurfaceHolder

interface PreviewImpl {



    fun getSurfaceHolder(): SurfaceHolder

    fun setcallback(call: Call)


    interface Call {

        fun onCall()
    }

}
