package com.moonayoung.call_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1000"));
                startActivity(intent); //system쪽으로 요청하는 것임. 안드로이드 OS안에 있는 액티비티 매니저가 정보를 받게됨.

                //intent라고 하는 정보는 시스템이 이해할 수 있게 해주는 객체다. 정보를 담아둘 수 있는 툴임.

                //Intent intent=new Intent();
                //ComponentName name=new ComponentName("com.moonayoung.call_intent","com.moonayoung.call_intent.MenuActivity"); //Component는 애플리케이션의 구성요소를 가리킴
                //intent.setComponent(name);
                //startActivityForResult(intent,101);

            }
        });
    }
}