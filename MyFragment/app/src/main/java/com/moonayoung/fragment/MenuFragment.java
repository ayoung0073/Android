package com.moonayoung.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment { //Activity를 하나 더 추가하면 AndroidManifest.xml에 추가해야되는데 이건 액티비티위에 추가하는 것이므로 따로 안 써도 됨.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, //onCreateView가 중요. ﻿콜백 메서드로 인플레이션이 필요한 시점에 자동 호출
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);
        //컨테이너에 fragment_menu의 xml파일을 올려라. 아직 완성 안되었으니 바로 붙이진 마라
        Button button=rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity=(MainActivity)getActivity();
                activity.onFragmentChanged(0);
            }

        });
        return rootView;

    }

}
