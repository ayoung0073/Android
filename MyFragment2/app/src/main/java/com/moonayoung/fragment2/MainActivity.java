package com.moonayoung.fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ImageSelectionCallback{

    ListFragment listFragment;
    ViewerFragment viewerFragment;

    int[] images={R.drawable.tailand1,R.drawable.tailand2,R.drawable.tailand3}; //drawable에 있는 것들은 타입이 int형임

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager=getSupportFragmentManager(); //Fragment자체를 변수에 저장하기 위해 Fragment매니저 가지고 옴.
        listFragment=(ListFragment) manager.findFragmentById(R.id.listFragment); //listFragment라는 프래그먼트를 찾아 저장
        viewerFragment=(ViewerFragment) manager.findFragmentById(R.id.viewerFragment);
    }

    @Override
    public void onImageSelected(int position) {
        viewerFragment.setImage(images[position]);
    }
}