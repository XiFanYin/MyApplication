package com.github.rxcamera.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.event.EventType
import kotlinx.android.synthetic.main.activity_costom_camera.*
import kotlinx.android.synthetic.main.activity_costom_camera.view.*
import java.lang.reflect.Type

class CostomCameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costom_camera)
        /*切换摄像头*/
        btn_qiehuan.setOnClickListener {

            rxCameraView.switchCamera()

        }

    }


    override fun onResume() {
        super.onResume()

        val config = Config.Builder.useFrontCamera().build()
        rxCameraView
                .openCamera(config)
                .subscribe({
                    when (it.type) {
                        EventType.PERVIEW -> {
                            Log.e("rrrrrrrr",it.cameraData.size.toString())
                        }


                    }
                })

    }


}
