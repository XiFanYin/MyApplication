package com.github.rxcamera.myapplication

import CameraUtils.CameraUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.rxcamera.myapplication.config.Config
import com.github.rxcamera.myapplication.manager.CameraManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener({



            CameraManager(this)
                    .openCamera()



        })
    }
}
