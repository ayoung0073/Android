package com.moonayoung.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    PersonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
       //GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager); //리싸이클러뷰에 레이아웃 매니저 설정하기

        adapter=new PersonAdapter();
        adapter.addItem((new Person("무나영","010-33**-66**")));
        adapter.addItem((new Person("양오진","010-27**-88**")));
        adapter.addItem((new Person("기미제","010-68**-55**")));
        //데이터를 추가한 후에 리싸이클러뷰에 어댑터를 설정해야함

        recyclerView.setAdapter(adapter); //리싸이클러뷰에 어댑터 설정하기 //setAdapter메서드

        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) { //메서드 구현하기(인터페이스)
                Person item=adapter.getItem(position);
                showToast("아이템 선택됨: "+item.getName());
            }
        });
    }

    public void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }
}