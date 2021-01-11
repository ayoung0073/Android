package com.moonayoung.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3); //3개의 프래그먼트만 할거라 제한함.

        class MyPagerAdapter extends FragmentStatePagerAdapter { //각 뷰페이지 안을 프래그먼트들로 구성할 건데, 그 프래그먼트들을 관리하는 것을 MyPagerAdapter이라 하겠다.
            ArrayList<Fragment> items = new ArrayList<Fragment>();

            public MyPagerAdapter(FragmentManager fm) {
                super(fm);
            }


            public void addItem(Fragment item) {
                items.add(item);
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return items.get(position);
            }

            @Override
            public int getCount() {
                return items.size();
            }
            // WOW return 0 하면 페이지 자체 보이지 않음. false로 인식하는 듯 한달만에 해결쓰(0906)


            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "페이지 " + position;
            }
        }


        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        Fragment1 fragment1 = new Fragment1();
        adapter.addItem(fragment1);

        Fragment2 fragment2 = new Fragment2();
        adapter.addItem(fragment2);

        Fragment3 fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(1);
            }
        });

    }
}

