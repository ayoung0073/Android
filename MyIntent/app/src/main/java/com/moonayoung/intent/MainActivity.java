package com.moonayoung.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                Button button=findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(), MenuActivity.class);
                        // Intent: MenuActivity 띄울 때 사용됨
                        // [첫번째 파라미터] getApplicationContext: 버튼이면 버튼이 포함되어있는 주변정보를 갖고 오는 것.
                        // ex) button이 어떤 레이아웃에 포함되어있는지, 실행되어진 환경변수라던가. UI객체는 context객체 무조건 받게 되어있음.

                        //[두번째 파라미터] MenuActivity.class : MenuActivity라는 클래스를 가리키는 클래스객체를 지정.

                        //--> Intent 객체가 MenuActivity 대한 정보를 참조하는 것임(reflection)
                        startActivityForResult(intent, 101);
                        //ForResult를 추가하면 화면을 띄울 때 화면으로부터 응답을 받고 싶을 때, (이거는 파라미터 하나 더 추가함. 101그대로돌아옴. 요청코드.)
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //on으로 시작되는 메서드는 자동호출됨.
        //이건 MenuActivity에서 MainActivity로 돌아올 때 자동호출 됨.
        super.onActivityResult(requestCode, resultCode, data);  //requestCode는 101이 그대로 돌아옴. resultCode는 Menu액티비티에서 온 RESULT_OK, data는 intent라는 객체가 옴.
        Toast.makeText(this,"메인화면으로 돌아옴",Toast.LENGTH_LONG).show();

        if(requestCode==101){
            if(data!=null){
                String name=data.getStringExtra("name");
                if(name!=null){
                    Toast.makeText(this,"응답으로 받은 데이터: "+name, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}