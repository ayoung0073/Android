package com.moonayoung.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectContacts();
            }
        });
        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    public void selectContacts()
    {
        Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        //pick->무언갈 선택하겠다
        //연락처 화면을 띄우기 위한 인텐트 만들기

        startActivityForResult(intent,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {
            if(requestCode==101)
            {
                Uri contactsUri=data.getData();
                //데이터를 가져올 때 Uri 객체로 반환됨
                String id=contactsUri.getLastPathSegment();
                //선택한 연락처의 id값 가져오기
                //id값 확인하는 이유 : 연락처의 상세 정보가 다른 곳에 저장되어 있음

                getContacts(id); //연락처정보꺼내오기, 기능: 조회에 해당하는 contentprovider필요

            }

        }
    }

    public void getContacts(String id)
    {
        Cursor cursor=null;
        String name="";

        try {
            cursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID + "=?",
                    new String[]{id},
                    null);
            // 파라미터 설명은 글로

            if (cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                println("Name: " + name);

                String columns[] = cursor.getColumnNames();
                for (String column : columns) {
                    int index = cursor.getColumnIndex(column);
                    String columnOutput = "#" + index + "->[" + column + "] " + cursor.getString(index);
                    println(columnOutput);
                }
                cursor.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void println(String data)
    {
        textView.append(data+"\n");
    }

    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this,"permissions denied: " + strings.length,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this,"permissions granted: " + strings.length,Toast.LENGTH_LONG).show();
    }
}
