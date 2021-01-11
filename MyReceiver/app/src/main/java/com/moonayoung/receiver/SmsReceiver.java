package com.moonayoung.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG="SMS_Receiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive 호출됨");

        Bundle bundle=intent.getExtras();
        SmsMessage[] messages=parseSmsMessage(bundle); //SmsMessage는 이미 정의되어있는 것.

        if(messages!=null && messages.length>0){
            String sender=messages[0].getOriginatingAddress();
            String contents=messages[0].getMessageBody();

            Log.d(TAG,"sender: "+sender+", contents: "+contents);

            sendToActivity(context,sender,contents);
        }
    }


    public void sendToActivity(Context context, String sender, String contents){ //context가 getApplicationContext()말하는 듯?
        Intent intent=new Intent(context,SmsActivity.class); //SmsActivity 클래스 실행시키기

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender",sender);
        intent.putExtra("contents",contents);

        context.startActivity(intent);
    }


    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs=(Object[]) bundle.get("pdus"); //pdus라는 키값으로 부가데이터 빼내기 //캐스팅쓰
        SmsMessage[] messages=new SmsMessage[objs.length];

        int smsCount=objs.length;
        for (int i=0;i<smsCount;i++){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
            {
                String format=bundle.getString("format");
                messages[i]=SmsMessage.createFromPdu((byte[])objs[i],format);
            }
            else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;

    }
}
