package com.github.rxcamera.myapplication

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener({
            RxPermissions(this)
                    .request(Manifest.permission.CAMERA)
                    .subscribe { aBoolean ->
                        if (aBoolean) {
                            startActivity(Intent(this, CostomCameraActivity::class.java))
                        }
                    }


        })
    }


}
