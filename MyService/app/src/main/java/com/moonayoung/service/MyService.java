package com.moonayoung.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

//app에 이 클래스 생성하면 AndroidManifest파일에 자동으로 생성됨 label태그에
public class MyService extends Service {
    private static final String TAG="MyService";

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate 호출됨");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { //startSevice라고 하는 메소드를 실행했을 때 서비스 실행됨.
        //MainActivity에서 전달한 데이터를 받아서 command를 통해..어떤 동작인지 구분함.
        Log.d(TAG, "onStartCommand 호출됨");

        if(intent!=null)
        {
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void processCommand(Intent intent){
        String command=intent.getStringExtra("command");
        String name=intent.getStringExtra("name");

        Log.d(TAG,"command: "+command+", name: "+name);


        try {
            Thread.sleep(5000); //5초동안 멈춰라
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        Intent showIntent=new Intent(getApplicationContext(),MainActivity.class); //액티비티를 띄우기 위한 인텐트 객체 만들기
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | //화면이 하나의 앱에서 계속 연속적으로 보여지게 만드는 것
                Intent.FLAG_ACTIVITY_SINGLE_TOP | //원래 탭이 있으면 그거를 보여주는 것.
                Intent.FLAG_ACTIVITY_CLEAR_TOP); //위에꺼를 없애조라


        showIntent.putExtra("command", "show");
        showIntent.putExtra("name",name+" from service");

        startActivity(showIntent); //addFlags를 안하면 Activity창이 또 뜨게 됨
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy 호출됨");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
