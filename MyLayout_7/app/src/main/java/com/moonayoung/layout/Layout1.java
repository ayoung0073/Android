package com.moonayoung.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Layout1 extends LinearLayout {

    ImageView imageView;
    TextView textView;
    TextView textView2;
    public Layout1(Context context) {
        super(context);

        init(context);
    }

    public Layout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //layout1.xml과 분리되어있기 때문에 연결시켜주는 작업함. 캐스팅도 해야함!
        //ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_main,container,false)이거는 프래그먼트에서
        //rootView 사용하는 거 이거는 프래그먼트 상속 받지 않았기ㅏ 때문에 rootView참조안하고 바로 findViewById메서드 호출 가능
        inflater.inflate(R.layout.layout1,this,true); //바로 연결해랑

        imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }

    public void setName(String name){
        textView.setText(name);
    }

    public void setMobile(String mobile){
        textView2.setText(mobile);
    }
}
