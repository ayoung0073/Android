package com.moonayoung.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
//실무에서 사용할 땐 소켓보다 웹방식을 많이 사용함
    EditText input1;
    TextView output1;
    TextView output2;

    Handler handler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1=findViewById(R.id.input1);
        output1=findViewById(R.id.output1);
        output2=findViewById(R.id.output2);

        Button sendButton=findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String data=input1.getText().toString(); //final을 붙여 상수를 만들면 Thread 클래스안에서 바로 참조 가능

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data); //스레드, 허가권한, 핸들러 **
                    }
                }).start(); //쓰레드 바로 시작
            }
        });

        Button startServerButton=findViewById(R.id.startServerButton);
        startServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start(); //스타트를 해야 run메서드 실행됨
            }
        });
    }

    public void printlnC(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                output2.append(data+"\n");
            }
        });
    }

    public void startServer(){
        int port=5001;

        try {
            ServerSocket server=new ServerSocket(port);

            while(true){ //로그식으로 출력
                Socket sock=server.accept();
                InetAddress clientHost=sock.getLocalAddress(); //Remote이 클라이언트, Local이 서버
                int clientPort=sock.getPort();
                println("클라이언트 연결됨 : "+clientHost+", "+clientPort);

                ObjectInputStream inputStream=new ObjectInputStream(sock.getInputStream());
                String input=(String) inputStream.readObject();
                println("데이터 받음 : "+input);

                ObjectOutputStream outputStream=new ObjectOutputStream(sock.getOutputStream());
                outputStream.writeObject(input+" from Server.");
                outputStream.flush();
                println("데이터 보냄");

                sock.close();
            }

        }catch(Exception e){
            e.getMessage();
        }

    } //끝냈구 인터넷권한부여해줘야함 -> AndroidManifest

    public void println(final String data){
        //쓰레드로 생성했으니 println이 핸들러를 사용해야함 final로 붙여주면 run메서드안에서 접근 가능
        handler.post(new Runnable() {
                         @Override
                         public void run() {
                             output1.append(data + "\n"); //append와 setText 차이
                         }
                     });
    }
    public void send(String data){ //클라이언트쪽 코드
        int port=5001;

        try {
            Socket sock = new Socket("localhost", port);

            printlnC("소켓 연결함");


            ObjectOutputStream outStream=new ObjectOutputStream(sock.getOutputStream());//데이터 전송
            outStream.writeObject(data);
            outStream.flush(); //버퍼에 남아있는거 출력
            printlnC("데이터 보냄: " + data);


            ObjectInputStream inputStream=new ObjectInputStream(sock.getInputStream());
            String input=(String)inputStream.readObject(); //객체를 읽어와서 String형으로 고쳐라
            printlnC(input);
            sock.close(); //꼭 소켓 닫아줘야함.

        }catch(Exception e){
            e.getMessage();
        }
    }
}