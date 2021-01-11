package com.moonayoung.challenge13;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StudyListFragment extends Fragment{
    TextView webHacking;
    TextView network;
    Button homeBT;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_studylist,container,false);

        webHacking = rootView.findViewById(R.id.textWebHacking);
        network = rootView.findViewById(R.id.textNetwork);
        homeBT = rootView.findViewById(R.id.homeBT5);

        final MenuActivity activity = (MenuActivity)getActivity();

        webHacking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //activity.onFragmentChanged("https://blog.naver.com/ayong0310/222066799764");
                activity.onFragmentChanged("blog_webHacking");

            }
        });

        webHacking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChanged("blog_network");
                //activity.onFragmentChanged("https://blog.naver.com/ayong0310/222059999019");

            }
        });

        homeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MenuActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
