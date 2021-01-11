package com.moonayoung.ex_event;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=findViewById(R.id.text); //id가 text인 View를 찾아서 TextView형인 text에 저장

        View view1=findViewById(R.id.view1);
        View view2=findViewById(R.id.view2);
        View view3=findViewById(R.id.view3);

        view1.setOnTouchListener(new View.OnTouchListener(){ //첫번째 View가 선택되었을 때
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action=motionEvent.getAction(); //getAction()은 int형을 return함

                if(action==MotionEvent.ACTION_DOWN){ //MotionEvent.ACTION_DOWN: 상수
                    println("첫번째칸 선택"); //println 메서드 수동으로 정의함
                }
                return true;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action=motionEvent.getAction();

                if(action==MotionEvent.ACTION_DOWN){
                    println("두번째칸 선택");
                }
                return true;
            }
        });

        view3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action=motionEvent.getAction();

                if(action==MotionEvent.ACTION_DOWN){
                    println("세번째칸 선택");
                }
                return true;
            }
        });

        text.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action=motionEvent.getAction();

                float curX=motionEvent.getX();
                float curY=motionEvent.getY();

                String txt="텍스트칸 선택됨 \n(" + curX + ", " + curY + ")";
                if (action==MotionEvent.ACTION_DOWN) {
                    println(txt);
                }
                return true;
            }
        });


    }

    public void println(String str){
        text.setText(str); //변수 str을 text의 텍스트로 지정
    }
}