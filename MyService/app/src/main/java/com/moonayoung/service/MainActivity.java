package com.moonayoung.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Intent를 통해 시스템을 요청해야됨. 거기에다 부가 data를 넣을 수 있음
    EditText editText;
    TextView textView;

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
                String name=editText.getText().toString();

                Intent intent=new Intent(getApplicationContext(),MyService.class); //onClick하면 MyService라는 클래스를 시작해라.//app에 Service클릭해 생성
                intent.putExtra("command","show"); //부가적인 데이터 전달? 이름이 "command"인 id란 아이디로 값 "show"를 전달
                intent.putExtra("name",name);
                startService(intent); //intent 서비스를 시작해라
                //startService는 서비스를 실행하는 것보다 데이터 전달하는 데에 많이 쓰임.
            }
        });

        Intent intent=getIntent();
        //액티비티가 새로 만들어질 때 전달된 인텐트 처리
        //메모리에 메인액티비티가 만들어진 상태라고 하면 이 함수가 호출되지 않음. 대신 onNewIntent함수를 호출해야함.
        processIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) { //이미 액티비티가 Create된 상태에서 intent를 가져올 때
        super.onNewIntent(intent);

        processIntent(intent);
    }

    public void processIntent(Intent intent){ //인텐트 처리하기
        if(intent!=null){
            String command=intent.getStringExtra("command");
            String name=intent.getStringExtra("name"); //"name"은 키값

            textView.append("command: "+command+", name: "+name +"\n");
            Toast.makeText(this, "command: "+command+", name: "+name, Toast.LENGTH_LONG).show();


        }
    }
}