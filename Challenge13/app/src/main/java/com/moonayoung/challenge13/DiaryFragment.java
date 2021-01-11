package com.moonayoung.challenge13;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;
import static java.sql.DriverManager.println;

public class DiaryFragment extends Fragment {

    Button diaryListBT;
    Button diaryWriteBT;
    Button homeBT;

    EditText editDiaryDate;
    EditText editDiaryContent;

    SQLiteDatabase database;

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_diary, container, false);
        //false 꼭 하기

        database= getActivity().openOrCreateDatabase("myApp.db",MODE_PRIVATE,null);
        database.execSQL("create table if not exists diary (_no INTEGER PRIMARY KEY autoincrement, date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, content text)");

        diaryListBT = rootView.findViewById(R.id.diaryListBT);
        diaryWriteBT = rootView.findViewById(R.id.diaryWriteBT);
        homeBT = rootView.findViewById(R.id.homeBT);

        editDiaryContent=rootView.findViewById(R.id.editDiaryContent);
        editDiaryDate=rootView.findViewById(R.id.editDiaryDate);


        homeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MenuActivity.class);
                startActivity(intent);
            }
        });


        diaryListBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaryList();
            }
        });

        diaryWriteBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        return rootView;
    }

    public void showDiaryList(){
        MenuActivity activity=(MenuActivity)getActivity();
        activity.onFragmentChanged("diaryList");
    }

    public void createDatabase(){
        try{
            String sql="create table if not exists diary (_no INTEGER PRIMARY KEY autoincrement, date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, content text);";
            database.execSQL(sql);
            return;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void insertData(){
        try{

            if(editDiaryContent==null || editDiaryDate ==null){
                showToast("내용을 다 작성해주세요");
                return;
            }

            else if(database==null){
                createDatabase();
            }

            else {
                showToast("잠시만 기다려주세요");
                String sql = "insert into diary (content) values ('"+editDiaryContent.getText().toString()+"')";
                database.execSQL(sql);
                showToast(sql+"일기 추가함");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void showToast(String str){
        Toast.makeText(getContext(),str,Toast.LENGTH_LONG).show();
    }
}
