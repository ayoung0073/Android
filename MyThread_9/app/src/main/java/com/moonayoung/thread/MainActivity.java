package com.moonayoung.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    //MainHandler handler;
    Handler handler=new Handler(); // API의 기본 핸들러 객체 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundThread thread=new BackgroundThread();
                thread.start();
            }
        });
        //handler=new MainHandler();
    }

    class BackgroundThread extends Thread{
        int value=0;
        public void run(){
            for(int i=0;i<100;i++){
                try{
                    Thread.sleep(1000);
                }catch(Exception e){}
                Log.d("MyThread","value: "+i);
                value+=1;

                //textView.setText("값: "+Integer.toString(value)); //메인스레드에서 관리하는 뷰를 접근할 수 없음 (동시접근.,, 해결하는거 다음 강의!) //핸들러!
//
//                Message message=handler.obtainMessage();
//                Bundle bundle=new Bundle();
//                bundle.putInt("value",value);
//                message.setData(bundle);
//
//                handler.sendMessage(message); //메시지를 보내면 mainHandler 클래스에게 전달됨

                handler.post(new Runnable() { // 핸들러의 새로운 Runnable 객체를 post메서드로 전달하여 호출
                    @Override
                    public void run() {
                        textView.setText("값: "+value);
                    }
                });

                handler.postDelayed(new Runnable() { //지연시간 두는 것.
                    @Override
                    public void run() {
                        textView.setText("값: "+value);
                    }
                },5000);

            }
        }
    }

    class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle bundle=msg.getData();
            int value=bundle.getInt("value");

            textView.setText("값: "+value);

        }
    }
}