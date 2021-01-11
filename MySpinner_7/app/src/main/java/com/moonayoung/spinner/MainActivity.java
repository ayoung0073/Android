package com.moonayoung.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    String[] items={"ayoung","marie","maum","maong","mimi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item, //API에서 제공하는 레이아웃
                items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //항목을 선택하기 위한 창이 따로 있기 때문에 항목을 선택하는 창을 위한 레이아웃 설정해주는 메서드

        spinner.setAdapter(adapter); //스피너에 어댑터 설정

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //리스너 설정
            //스피너 객체가 아이템 선택 이벤트를 처리할 수 있도록 사용하는 리스너 -> OnItemSelectedListener
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setText(items[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("");
            }
        });
    }
}
