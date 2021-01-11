package com.moonayoung.vibrate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    NotificationManager manager;

    private static String CHANNEL_ID = "channel1";
    private static String CHANNEL_NAME = "Channel1";

    private static String CHANNEL_ID2 = "channel2";
    private static String CHANNEL_NAME2 = "Channel2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                // 시스템 서비스 객체를 사용하므로 new 연산자 X
                // getSystemService는 Object형 반환하므로 캐스팅 해야함

                if(Build.VERSION.SDK_INT >= 26){
                    vibrator.vibrate(VibrationEffect.createOneShot(1000,10));
                    // 파라미터1: 지속시간, 파라미터2: 음량
                }else{
                    vibrator.vibrate(1000);
                }
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),uri);
                // 소리를 울리기 위해서 Ringtone 객체 참조
                ringtone.play();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.beep);
                //player.start();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoti1();
            }
        });

        // 버튼 눌렀을 때 PendingIntent를 만들어 Notification을 만들 때 설정
        // Intent와 비슷하지만 PendingIntent는 시스템에서 대기하는 역할
        // 원하는 상황 만들어졌을 때 시스템에 의해 해석되고 처리
         Button button5 = findViewById(R.id.button5);
         button5.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showNoti2();
             }
         });
    }

    public void showNoti1(){
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(manager.getNotificationChannel(CHANNEL_ID) == null) {
                manager.createNotificationChannel(new NotificationChannel(
                        CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
                ));
            }
                builder = new NotificationCompat.Builder(this,CHANNEL_ID);

        }else{
            builder = new NotificationCompat.Builder(this);
        }

        builder.setContentTitle("TEST");
        builder.setContentText("알림 TEST 메시지입니다");
        builder.setSmallIcon(android.R.drawable.ic_menu_view);
        Notification noti = builder.build();
        // NotificationCompat.Builder 객체가 먼저 title과 text 모두 설정한 후 Notification 객체 생성하는 것
        // build 메서드를 호출해 Notification 객체 생성

        manager.notify(1, noti);
        // 상단알림 띄우기
    }

    public void showNoti2() {
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(manager.getNotificationChannel(CHANNEL_ID2) == null){ //notification 채널이 없을 때
                 manager.createNotificationChannel(new NotificationChannel( // notification 채널 생성
                    CHANNEL_ID2, CHANNEL_NAME2, NotificationManager.IMPORTANCE_DEFAULT
                 ));
            }
            builder = new NotificationCompat.Builder(this,CHANNEL_ID2);
        }else{
            builder = new NotificationCompat.Builder(this);
        }

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentTitle("TEST2");
        builder.setContentText("알림 TEST2 메세지입니다");
        builder.setSmallIcon(android.R.drawable.ic_menu_view);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent); // 빌더에 PendingIntent 객체 설정

        Notification noti = builder.build();

        manager.notify(2,noti);
        // 상단 알림 띄우기
    }
}