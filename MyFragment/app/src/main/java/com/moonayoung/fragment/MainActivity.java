package com.moonayoung.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment=(MainFragment)getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        //프래그먼트 매니저에게 id가 mainFragment인 것을 찾아 캐스팅해서 mainFragment에 저장
        menuFragment=new MenuFragment();
        //맨처음 화면의 FrameLayout에 id가 mainFragment Fragment가 있기 때문에, menuFragment는 나중에 버튼을 클릭할 때 생성되기 때문에 새로 객체를 만들어줘야한다.
    }

    public void onFragmentChanged(int index){
        if(index==0)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
            //Transaction은 여러개의 명령어가 있을 때 한꺼번에 실행할 수 있도록 해주는 메서드
            //기본적으로 프래그먼트를 처리하는 것은 Transaction이 한다고 보면 됨
        }
        else if(index==1)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,menuFragment).commit();
        }
    }
}