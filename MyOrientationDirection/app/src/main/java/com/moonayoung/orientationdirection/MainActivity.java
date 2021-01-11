package com.moonayoung.orientationdirection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String name;
    EditText editText;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToast("onCreate 호출됨");

        editText = findViewById(R.id.editText);
        textView2=findViewById(R.id.textView2);

        Button button = findViewById(R.id.button);
        if(button!=null) { //가로방향으로 전환했을 때 nullException 생겼었음
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (editText != null) {
                        name = editText.getText().toString(); //toString 메서드를 통해 String형으로 고쳐야함!
                        showToast("사용자가 입력한 값을 name 변수에 할당함");
                    }
                }
            });
        }

        if(savedInstanceState!=null){
            if(textView2!=null){
                name=savedInstanceState.getString("name"); //savedInstanceState의 id가 "name"인 값을 String형으로 바꿔서 name에 저장
                textView2.setText(name); //가로방향일 때 있는 textView2에 텍스트 지정

                showToast("값을 복원했습니다: "+name);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name",name); //번들이라는 객체는 데이터를 담아두기 위한 것(나중에 많이 봄)
        //activity라고 하는 것이 메모리에 없어지는 순간에 이 함수가 호출되면서 이 값이 저장됨
        //onCreate()메서드가 호출될 때 파라미터로 전달됨.
        //onCreate() 메서드의 파라미터는 savedInstanceState라는 이름으로 되어있고, 이 객체에서 데이터를 가져와 name 변수에 다시 할당하면 데이터를 복구하게 됨

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        showToast("onDestroy 호출됨");
    }

    public void showToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}