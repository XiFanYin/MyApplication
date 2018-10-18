package com.github.rxcamera.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_costom_camera.*
import kotlinx.android.synthetic.main.activity_costom_camera.view.*

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

        rxCameraView
                .openCamera()
                .subscribe({
                    Log.e("rrrrrrrrrr", it.size.toString())
                })

    }


}
