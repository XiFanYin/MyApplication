/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.rxcamera.myapplication.perview

import android.app.Activity
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.FrameLayout
import com.github.rxcamera.myapplication.R

class SurfaceViewPreview(context: Activity, rongqi: FrameLayout) : PreviewImpl {

    lateinit var call: PreviewImpl.Call
    override fun setcallback(call: PreviewImpl.Call) {
        this.call = call
    }

    val mSurfaceView: SurfaceView

    init {
        val view = View.inflate(context, R.layout.surface_view, rongqi)
        mSurfaceView = view.findViewById(R.id.surface_view) as SurfaceView
        val holder = mSurfaceView.getHolder()
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(h: SurfaceHolder) {
                call.onCall()
            }

            override fun surfaceChanged(h: SurfaceHolder, format: Int, width: Int, height: Int) {


            }

            override fun surfaceDestroyed(h: SurfaceHolder) {

            }
        })

    }


    override fun getSurfaceHolder(): SurfaceHolder {
        return mSurfaceView.holder
    }

}
