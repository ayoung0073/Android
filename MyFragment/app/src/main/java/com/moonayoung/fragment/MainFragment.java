package com.moonayoung.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, //onCreateView가 중요쓰. 이게 그 두개가  연결되는 거임
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_main, container, false);
        //xml의 내용을 메모리에 올려주는 과정

        Button button=rootView.findViewById(R.id.button); //여기서 다른 점은 rootView라는 변수를 붙여서 메서드 이용
        //위에서 ViewGroup 클래스로 선언한 rootView변수에 fragment_menu.xml 정보 넣음.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //주의할 것: 프래그먼트는 항상 액티비티 위에 올라가있는겨!
                MainActivity activity=(MainActivity)getActivity();      //올라가있는 액티비티 참조하고플 때
                activity.onFragmentChanged(1);
                //0을 넣으면 MainFragment띄어주고, 1을 띄우면 MenuFragment를 띄어주는 함수 만듬
                //MainActivity의 메서드
            }
        });

        return rootView;
    }
}