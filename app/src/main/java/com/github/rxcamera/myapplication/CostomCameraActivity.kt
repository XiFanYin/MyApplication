package com.github.rxcamera.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_costom_camera.*

class CostomCameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costom_camera)

        rxCameraView.openCamera()


    }


}
