package com.moonayoung.snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button); //스낵바 버튼
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { // 버튼을 클릭했을 때
                Snackbar.make(view,"스낵바입니다",Snackbar.LENGTH_LONG).show(); //"스낵바입니다"라는 메시지와 함께 스낵바 띄움
            }
        });

        Button button2=findViewById(R.id.button2); // 대화상자 버튼
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showMessage(); //수동으로 정의한 showMessage 메서드 호출
            }
        });
    }

    public void showMessage(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this); //대화상자를 만들기 위한 Builder 객체 생성
        builder.setTitle("안내"); //제목 지정
        builder.setMessage("알림상자를 누르셨습니까?"); //메시지 지정
        builder.setIcon(android.R.drawable.ic_dialog_alert); //아이콘 지정

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() { // 예 버튼을 클릭했을 때
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { //int는 예, 아니오, 취소 등등을 가리는 번호.
                Toast.makeText(getApplicationContext(),"예 버튼 눌림 "+i,Toast.LENGTH_LONG).show(); //예는 1임
            }
        });

        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"취소 버튼 눌림 "+i,Toast.LENGTH_LONG).show(); // 취소는 3
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"아니오 버튼 눌림 "+i,Toast.LENGTH_SHORT).show(); //아니오는 2임
            }
        });
        AlertDialog dialog=builder.create(); //대화상자 객체 생성 후 보여주기
        dialog.show();
    }
}