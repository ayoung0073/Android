package com.moonayoung.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater=getLayoutInflater(); //레이아웃 인플레이터 객체를 참조

                View layout=inflater.inflate( //토스트를 위한 레이아웃 인플레이션
                        R.layout.toastborder, //toastborder 레이아웃
                        (ViewGroup) findViewById(R.id.toast_layout_root)); //toastborder레이아웃에 id가 toast_layout_root인 레이아웃을 저장

                TextView text=layout.findViewById(R.id.text);
                Toast toastView=new Toast(getApplicationContext()); // 토스트 객체만 일단 생성(this라고 하면 에러남, onClickListener가 this라서 그런듯?)
                text.setText("Toast Changed"); // 텍스트 지정해줌
                toastView.setGravity(Gravity.BOTTOM|Gravity.LEFT, 100,-400); //탑 래프트기준으로
                toastView.setDuration(Toast.LENGTH_LONG); // 길게 보여줌
                toastView.setView(layout); //layout은 toastborder에 있는. toast.xml은 toastborder 레이아웃의 백그라운드 모양을 꾸며줌
                toastView.show();
            }
        });
    }
}