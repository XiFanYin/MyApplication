package com.github.rxcamera.myapplication

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_costom_camera.*

class CostomCameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costom_camera)
        /*打开相机*/
        rxCameraView.openCamera().subscribe({ image.setImageBitmap(BitmapFactory.decodeByteArray(it,0,it.size)) })


        /*切换摄像头*/
        btn_qiehuan.setOnClickListener {
            rxCameraView.switchCamera()
        }

    }


}
