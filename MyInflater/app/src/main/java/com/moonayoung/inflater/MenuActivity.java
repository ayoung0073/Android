package com.moonayoung.inflater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    LinearLayout container; //manifests에 있는 AndroidManifest.xml이 정보를 보여줌 거기에서 화면 처음에 뭐 띄울지 바꿀 수 있음.
    TextView sub_text; //부분레이아웃파일에 있는 sub_text를 참조할 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        container=findViewById(R.id.container);

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                addLayout(); //수동으로 정의한 addLayout 메서드 호출
            }
        });
    }

    public void addLayout(){
        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); //캐스팅
        //이미 시스템 서비스를 동작하고 있는 것을 참조하는 것임. new 연산자 x
        //getSystemService메서드를 이용해 LayoutInflater 객체를 참조하고 캐스팅한 후, inflater을 사용함.

        inflater.inflate(R.layout.sub1,container,true);
        //inflation 한 후에 R.layoute에 있는 sub1.xml 파일을 id가 container인 LinearLayout 영역에 넣어달라.

        Toast.makeText(this,"부분 화면이 추가되었습니다.",Toast.LENGTH_SHORT).show();
        //토스트로 확인

        sub_text=container.findViewById(R.id.sub_text);
        // 부분 레이아웃은 container 객체에 설정되었으므로 container 객체의 findViewById() 메서드를 사용해야함.
        Button button3=container.findViewById(R.id.button3); //부분화면에 있는 버튼을 가져옴
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sub_text.setText("부분화면의 버튼을 클릭하였습니다."); //부분화면안에 있는 TextView의 text를 설정함
            }
        });
    }
}