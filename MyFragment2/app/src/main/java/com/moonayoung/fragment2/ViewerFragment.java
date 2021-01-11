package com.moonayoung.fragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ViewerFragment extends Fragment {
    ImageView img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment_viewer,container,false);
        //파라미터 순서 구분하기(보일 프래그먼트, 담은 컨테이너, 아직 붙이지마라)

        img=rootView.findViewById(R.id.imageView);

        return rootView;
    }

    public void setImage(int resId){ //이미지를 가리키는 타입이 정수형임
        img.setImageResource(resId); //이미지 id값을 파라미터로 넣어주면 이미지뷰가 바뀜

    }
}
