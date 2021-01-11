package com.moonayoung.capture;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class CameraServiceView extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder holder;
    Camera camera=null; //하드웨어로

    public CameraServiceView(Context context) {
        super(context);

        init(context);
    }

    public CameraServiceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context){
        holder=getHolder();
        holder.addCallback(this);   //구현한 콜백인터페이스객체가 카메라서비스뷰가됨

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        camera=camera.open();
        try {
            camera.setPreviewDisplay(holder);//홀더에서 서비스뷰설정???
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        camera.startPreview();//카메라 미리보기
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera=null;
    }

    public boolean capture(Camera.PictureCallback callback){
        if(camera!=null){
            camera.takePicture(null,null,callback); //하드웨어쪽에있는카메라메서드
            return true;
        }
        else
        {
            return false;
        }
    }
}
