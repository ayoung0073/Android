package com.moonayoung.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);

        Log.d("Main","onCreate 호출됨");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("Main","onStart 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Main","onStop 호출됨");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Main","onDestroy 호출됨");
    }

    @Override
    protected void onPause() { //전화오면 멈출 때
        super.onPause();

        saveState(); //멈추면 상태를 저장하는 saveState 메서드 호출

        Log.d("Main","onPause 호출됨");
    }

    @Override
    protected void onResume() { //다시 돌아올 때
        super.onResume();

        loadState(); //다시 돌아올 때 저장되어있던 상태를 복원하는 메서드 호출

        Log.d("Main","onResume 호출됨");
    }

    public void saveState(){
        SharedPreferences pref=getSharedPreferences("pref", Activity.MODE_PRIVATE); // 간단하게 저장하는 SharedPreference
        SharedPreferences.Editor editor=pref.edit(); //객체 만들어짐
        editor.putString("name",editText.getText().toString()); //입력상자에 입력한 글자를 가져옴
        //"pref"라는 이름의 키값을 찾아서 거기에 editText값을 저장함.
        editor.commit(); //이걸 해야 파일로 저장됨(단말 내부에)
    }

    public void loadState(){
        SharedPreferences pref=getSharedPreferences("pref",Activity.MODE_PRIVATE);
        //"pref"라는 이름의 공간을 찾아서 거기에 있는 값을 pref에 저장함
        if(pref!=null){
            String name=pref.getString("name",""); //pref의 문자열을 가져오는데 없으면 ""로 저장.
            editText.setText(name);
            Toast.makeText(this,"저장되어있는 값을 가져옴",Toast.LENGTH_LONG).show();
        }
    }

}