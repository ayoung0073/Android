package com.moonayoung.challenge13;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    TextView profileUrl1;
    TextView profileUrl2;
    TextView profileUrl3;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_profile,container,false);

        Button homeBT=rootView.findViewById(R.id.homeBT);

        profileUrl1=rootView.findViewById(R.id.profileUrl1);
        profileUrl2=rootView.findViewById(R.id.profileUrl2);
        profileUrl3=rootView.findViewById(R.id.profileUrl3);


        homeBT.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getContext(),MenuActivity.class);
                     startActivity(intent);
                }
            });

        final MenuActivity activity=(MenuActivity)getActivity();

        profileUrl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChanged("blog");
            }
        });

        profileUrl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent=new Intent(getContext(),UrlFragment.class);
                //intent.putExtra("url","http://www.instagram.com/__a_young");
                //startActivity(intent);
                activity.onFragmentChanged("insta");
            }
        });

        return rootView;
    }

}
