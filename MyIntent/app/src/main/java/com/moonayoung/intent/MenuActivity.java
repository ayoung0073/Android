package com.moonayoung.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button=findViewById(R.id.button); //Button 객체를 참조
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //실제로 안드로이드에서는 화면이 하나 실행되고 거기서 자기 화면은 사라지지 않고, 밑에 깔려있게 됨. 그래서 이건 main화면 위에 깔려있는거임
                Intent intent=new Intent(); //Intent 상당히 중요
                intent.putExtra("name","mike");
                // 부가데이터 집어넣는다. intent를 통해 다른 activity로 정보를 전달할 수 있음.
                //name이라고하는 키값으로 mike라는 값을 넣는다.
                setResult(RESULT_OK,intent);
                //응답 보내기. RESULT_OK로 정상 응답으로 구분할 수 있음. 200..이런거 해도 ㅇ


                finish(); //현재 액티비티 없애기,
                          // finish()를 하면 이전 화면인 main 화면이 보여지게 됨.
            }
        });
    }
}