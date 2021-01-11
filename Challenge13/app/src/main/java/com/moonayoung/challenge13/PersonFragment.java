package com.moonayoung.challenge13;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class PersonFragment extends Fragment {
    int customerNum;

    Button homeBT;

    TextView textCustomerNum;
    EditText editName;
    EditText editMobile;

    PersonAdapter adapter;

    SQLiteDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_person, container, false);

        final RecyclerView recyclerView=rootView.findViewById(R.id.recyclerView);

        adapter=new PersonAdapter();


        LinearLayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);



        database= getActivity().openOrCreateDatabase("myApp.db",MODE_PRIVATE,null);
        try{
            database.execSQL("create table if not exists guest_contacts (name text PRIMARY KEY NOT NULL, contact text);");
        }catch(Exception e){
            e.printStackTrace();
        }



        editName=rootView.findViewById(R.id.editName);
        editMobile=rootView.findViewById(R.id.editMobile);
        textCustomerNum=rootView.findViewById(R.id.textCustomerNum);


        String sql="select name,contact from guest_contacts";
        try {
            Cursor cursor = database.rawQuery(sql, null);
            final int recordCount = cursor.getCount();

            if (recordCount != 0) {
                for (int i = 0; i < recordCount; i++) {
                    cursor.moveToNext();

                    String name = cursor.getString(0);
                    String contact = cursor.getString(1);

                    Person person = new Person(i + 1, name, contact);
                    adapter.addItem(person);
                }
            }

                recyclerView.setAdapter(adapter);
                Toast.makeText(getActivity(), "record개수: " + recordCount, Toast.LENGTH_LONG).show();
                textCustomerNum.setText(recordCount + "명");

                cursor.close();

            }catch(Exception e){
                e.printStackTrace();
           }


        Button button=rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editName!=null && editMobile!=null) {
                    Toast.makeText(getActivity(),"고객 정보가 추가되었습니다",Toast.LENGTH_LONG).show();
                    customerNum++;
                    adapter.addItem(new Person(customerNum,editName.getText().toString(),editMobile.getText().toString()));
                    String sql="insert into guest_contacts (name, contact) values ('" + editName.getText().toString() +"','" +editMobile.getText().toString()+"');";
                    try {
                        database.execSQL(sql);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    Toast.makeText(getActivity(),"연락처 추가함",Toast.LENGTH_LONG).show();



                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                    textCustomerNum.setText(customerNum + "명");

                    editName.setText("이름");
                    editMobile.setText("전화번호");

                }
            }
        });

        homeBT=rootView.findViewById(R.id.homeBT);
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