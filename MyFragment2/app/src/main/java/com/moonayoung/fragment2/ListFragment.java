package com.moonayoung.fragment2;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {
    ImageSelectionCallback callback;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof ImageSelectionCallback){ //만약 context가 저 인터페이스를 구현한 것이라면.
            callback=(ImageSelectionCallback) context; //그럼  Activity가 들어가있다는거
        }
    }

    @Nullable
    @Override //Fragment니까 ViewGroup 클래스를 이용해서 메모리에 저장시키기.
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment_list,container,false); //당장 붙이는 게 아니니까 false

        Button button=rootView.findViewById(R.id.button); //프래그먼트는 ViewGroup클래스 참조변수를 통해서 findViewById메서드 호출
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MainActivity activity=(MainActivity) getActivity(); //Fragment클래스는 getActivity라는 메서드가 있다는 것을 알 수 있음
//                activity.onImageSelected(0);
//                //activity의 메서드 호출 만약 다른 액티비티에서 이 프래그먼트가 올라가면 타입을 항상 바꿔줘야함 그래서 onAttach 메서드.?
                  //그래서 위에 onAttach에서 함 그래서 뒤 코드로.

                if(callback!=null){
                    callback.onImageSelected(0); //images배열의 index말하는 거 (0)
                }
            }
        });

        Button button2=rootView.findViewById(R.id.button2); //프래그먼트는 ViewGroup클래스 참조변수를 통해서 findViewById메서드 호출
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onImageSelected(1);
                }
            }
        });

        Button button3=rootView.findViewById(R.id.button3); //프래그먼트는 ViewGroup클래스 참조변수를 통해서 findViewById메서드 호출
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(callback!=null){
                        callback.onImageSelected(2);
                }

            }
        });

        return rootView;
    }
}
