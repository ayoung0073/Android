package com.moonayoung.tab;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar); //나는 안해야 뜸

        ActionBar actionBar = getSupportActionBar(); //액션바 가져올 수 있음
        actionBar.setDisplayShowTitleEnabled(false);  //기존 title 지우기(MyTab 이라고 적혀있는 거)

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        TabLayout tabs = findViewById(R.id.tabs);

        tabs.addTab(tabs.newTab().setText("통화기록")); //아직 탭이없으니까 Tab 새로 만들고, tabs에 추가하기
        tabs.addTab(tabs.newTab().setText("스팸기록"));
        tabs.addTab(tabs.newTab().setText("연락처"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { //탭을 위한 이벤트 처리.
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition(); //몇번째 탭을 눌렀는지

                if (position == 0) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                } else if (position == 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                } else if (position == 2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}