package com.moonayoung.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img1;
    ImageView img2;

    int imgIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //여기 안에서 변수 초기화해야함
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1=findViewById(R.id.imageView1);//id를 이용해서 이미지를 찾아라.
        img2=findViewById(R.id.imageView2);
    }

    public void onButtonClicked(View v){
        changeImage();
    }

    public void changeImage(){
        if(imgIndex==0)
        {
            img2.setVisibility(View.INVISIBLE);
            img1.setVisibility(View.VISIBLE);

            imgIndex=1;
        }
        else if(imgIndex==1)
        {
            img2.setVisibility(View.VISIBLE);
            img1.setVisibility(View.INVISIBLE);

            imgIndex=0;
        }

    }
}