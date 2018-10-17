package com.github.rxcamera.myapplication.camera

import com.github.rxcamera.myapplication.perview.PreviewImpl


class Camera1 (preview: PreviewImpl): CameraImpl {



    override fun openCamera(): Boolean {
        return false
    }

}
