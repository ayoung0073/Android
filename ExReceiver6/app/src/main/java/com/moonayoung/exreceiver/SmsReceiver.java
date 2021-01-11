package com.moonayoung.exreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;

import java.util.Objects;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle=intent.getExtras(); //Bundle객체에 intent의 부가 데이터 가져오기
        SmsMessage[] messages=parseSmsMessage(bundle);

    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object objs[]=(Objects[])bundle.get("pdus");
        SmsMessage[] messages=new SmsMessage[objs.length];


        int smsCount=objs.length;

        for(int i=0;i<smsCount;i++)
        {

        }
    }
}
