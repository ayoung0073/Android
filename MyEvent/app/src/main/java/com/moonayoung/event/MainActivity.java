package com.moonayoung.event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity { //AppCompatActivity를 상속받은 MainActivity
    TextView textView;
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Activity화면만들어질 떄 자동으로
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //레이아웃파일이 소스코드와 같이 합쳐진다. (R.layout.레이아웃 파일 이름.) R: res.

        textView=findViewById(R.id.textView);

        View view=findViewById(R.id.view); //id가 view인 View 클래스를 view변수에 저장.
        view.setOnTouchListener(new View.OnTouchListener() { //단일 뷰의 터치 이벤트 캡쳐  //View.OnTouchListener객체를 View 객체에 연결할 수 있음
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) { //onTouch메서드 오버라이딩
                int action=motionEvent.getAction(); //getAction()메서드는 int를 반환

                float curX=motionEvent.getX(); //event가 발생한 곳의 위치
                float curY=motionEvent.getY();

                if(action==MotionEvent.ACTION_DOWN) { //손가락을 누른 상태 MotionEvent.ACTION_DOWN: 상수임
                    println("손가락 눌림: " + curX + ", " + curY);
                }
                else if(action==MotionEvent.ACTION_MOVE){
                    println("손가락 움직임: "+curX+", "+curY);
                }
                else if(action==MotionEvent.ACTION_UP){
                    println("손가락 뗌: "+curX+", "+curY);
                }
                return true;
            }
        });

        detector=new GestureDetector(this, new GestureDetector.OnGestureListener() { //일반적인 동작 감지
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                println("onDown 호출됨");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                println("onShowPress 호출됨");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onScroll 호출됨: "+v+", "+v1);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                println("onLongPress 호출됨");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onFling 호출됨: "+v+", "+v1);
                return true;
            }
        });


        View view2=findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() { //view2도 onTouch 메서드 재정의
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) { //onTouch메서드안에 onTouchEvent호출(이게 위에 GestureDectector에서 정의한 함수들)
                detector.onTouchEvent(motionEvent); //GestureDetector 객체는 onTouchEvent()메서드를 재정의하고 관찰된 모든 이벤트를 감지기 인스턴스에 전달.
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //키이벤트 처리
        if(keyCode==event.KEYCODE_BACK){
            println("시스템 [BACK] 버튼이 눌렸어요.");
            return true;
        }
        return false;
    }

    public void println(String str){
        textView.append(str+"\n");
    }
}