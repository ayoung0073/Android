package com.moonayoung.challenge13;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

public class DiaryListFragment extends Fragment {

    RecyclerView diaryRecyclerView;
    Button backBT;
    Button homeBT;

    DiaryAdapter adapter;

    SQLiteDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_diary_list,container,false);
        diaryRecyclerView=rootView.findViewById(R.id.diaryRecyclerView);

        LinearLayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false); //외우기...
        diaryRecyclerView.setLayoutManager(manager);


        adapter=new DiaryAdapter();
        backBT=rootView.findViewById(R.id.backBT);

        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuActivity activity=(MenuActivity)getActivity();
                activity.onFragmentChanged("diary");
            }
        });

        homeBT=rootView.findViewById(R.id.homeBT4);
        homeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

        try { //try문 하면 더 안정된 코드로 만들 수 ㅇ
            database = getActivity().openOrCreateDatabase("myApp.db", MODE_PRIVATE, null); //데이터베이스 생성하기
        }catch(Exception e) {
            e.printStackTrace();
        }
        String sql="select date, content from diary";
        try {
            Cursor cursor = database.rawQuery(sql, null);
            int recordCount = cursor.getCount();

            for (int i = 0; i < recordCount; i++) {
                cursor.moveToNext();


                String date = cursor.getString(0);
                String content = cursor.getString(1);

                Diary diary = new Diary(date, content);
                adapter.addItem(diary);
            }

            diaryRecyclerView.setAdapter(adapter);
            Toast.makeText(getActivity(),"record개수: "+recordCount,Toast.LENGTH_LONG).show();

            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rootView;
    }
}
