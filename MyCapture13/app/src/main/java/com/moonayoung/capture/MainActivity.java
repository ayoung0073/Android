package com.moonayoung.capture;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    CameraServiceView serviceView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.imageView);

        FrameLayout container=findViewById(R.id.container);
        serviceView=new CameraServiceView(this);
        container.addView(serviceView);

        AutoPermissions.Companion.loadAllPermissions(this,101); //실제로는 onDenied,onGranted이런거 오버라이딩해야함
        //이거 위치 중요 capture()메서드 호출한 후에 하면 위험권한부여가 잘 안됨 그전에 해야함

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capture();
            }
        });

    }

    public void capture(){
        serviceView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Bitmap bitmap=BitmapFactory.decodeByteArray(data,0,data.length);
                imageView.setImageBitmap(bitmap);

            }
        });
    }

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }
}