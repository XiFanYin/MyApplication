package com.github.rxcamera.myapplication.event;

public class BaseEvent {
    /*相机数据，包括预览数据和拍照数据*/
    public byte[] cameraData;
    /*下流类型*/
    public EventType type;

    public BaseEvent(byte[] cameraData, EventType type) {
        this.cameraData = cameraData;
        this.type = type;
    }


}
