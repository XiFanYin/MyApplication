package com.github.rxcamera.myapplication.config

import CameraUtils.CameraUtil

class Config(builder: Builder) {
    //初始化打开摄像头的id
    var currentCameraId = -1

    init {
        currentCameraId = builder.currentCameraId
    }


    object Builder {
        var currentCameraId = -1
        /**
         * 使用前置摄像头
         */
        fun useFrontCamera(): Builder {
            currentCameraId = CameraUtil.getBackCameraId()
            return this
        }

        /**
         * 使用后置摄像头
         */
        fun useBackCamera(): Builder {
            currentCameraId = CameraUtil.getBackCameraId()
            return this
        }


        fun build(): Config = Config(this)


    }
}