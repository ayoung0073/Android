package com.moonayoung.http;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    // 네트워킹은 Manifest에 Internet권한(<uses~ 태그) 허용해야함, 웹은 usesCleartextTraffic까지 true 추가해줘야함(application 태그에)
    EditText editText;
    TextView textView;

    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String urlStr=editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr); //웹으로 요청하는 거니까 쓰레드 생성하기
                    }
                }).start();
            }
        });
    }

    public void request(String urlStr){ // 자바는 HttpUrlConnection을 기본적으로 사용

        try {
            StringBuilder builder=new StringBuilder(); //StringBuilder 객체는 String을 붙여가면서 쓸 수 있음
            URL url = new URL(urlStr);

            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            // URL객체의 openConnection 메서드를 이용해 HttpURLConnection 객체 생성
            if(conn!=null){
                conn.setConnectTimeout(10000); //연결 대기 시간 설정 ( 10초 동안 연결되기를 기다림 )
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                //요청 보내고 받는 것 중에 받는 것을 처리하겠다
                // HttpURLConnection 객체의 입력이 가능하도록 만들어줌

                int resCode=conn.getResponseCode(); //이때 요청 보내고 응답 받음

                BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                //데이터를 읽어들임
                String line=null;

                while(true){
                    line=reader.readLine();
                    if(line==null){
                        break;
                    }
                    builder.append(line+"\n");
                }
                reader.close();
                conn.disconnect();
            }
            println("응답 -> "+builder.toString());

        }catch (Exception e){
            e.getMessage();
        }
    }

    public void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data+"\n");
            }
        });
    }

}