package com.github.rxcamera.myapplication.config

import CameraUtils.CameraUtil

/*相机配置文件*/
class Config private constructor(builder: Builder) {

    //初始化打开摄像头的id
    var currentCameraId = -1
    //散光灯模式
    var flashModel = FlashModel.FLASH_OFF


    init {
        currentCameraId = builder.currentCameraId
        flashModel = builder.flashModel
    }

    object Builder {

        var currentCameraId = -1
        var flashModel = FlashModel.FLASH_OFF
        /**
         * 使用前置摄像头
         */
        fun useFrontCamera(): Builder {
            currentCameraId = CameraUtil.getFrontCameraId()
            return this
        }

        /**
         * 使用后置摄像头
         */
        fun useBackCamera(): Builder {
            currentCameraId = CameraUtil.getBackCameraId()
            return this
        }


        fun flashModel(model: FlashModel): Builder {
            flashModel = model
            return this
        }


        fun build(): Config = Config(this)

    }





}