package com.moonayoung.progress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    int value=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.progressBar);
        final TextView textview=findViewById(R.id.textView);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() { //버튼 클릭했을 때
            @Override
            public void onClick(View view) {
                value+=10; //10씩 더함
                if(value==100){ //10씩 더해서 value의 결과값이 100이 되면
                    textview.setText("100% 달성함!");
                }
                if(value>100){ //100 초과이면 textView를 공백으로 둠
                    textview.setText("");
                    value=0;
                }
                progressBar.setProgress(value); //더해진 value값을 현재값으로 설정
            }
        });
    }
}