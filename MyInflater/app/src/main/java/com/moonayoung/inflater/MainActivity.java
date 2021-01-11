package com.moonayoung.inflater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //R.layout.activity_main: res폴더 밑에 layout폴더 밑에 activity_main.xml 가리킴
        //메모리에 버튼이 만들어지는 자체가 이 함수 이후로 나타나는 거임. 밑으로 가면 버튼 못 찾음.
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
    }
}