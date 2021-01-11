package com.moonayoung.cumstomview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        CustomView view=new CustomView(this);
        setContentView(view); //뷰를 메인액티비티의 레이아웃파일이 아닌 CustomView의 View를 보이게 함

    }
}
