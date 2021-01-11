package com.moonayoung.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    TextView textView;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        textView=findViewById(R.id.textView);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //데이터베이스 생성 버튼
                String databaseName=editText.getText().toString();
                createDatabase(databaseName);
            }
        });

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() { //테이블 생성 버튼
            @Override
            public void onClick(View view) {
                String tableName=editText2.getText().toString();
                createTable(tableName);
            }
        });

        Button button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //데이터베이스 생성 버튼
                insertRecord();
            }
        });

        Button button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //데이터베이스 생성 버튼
                executeQuery();
            }
        });

    }

    public void createDatabase(String databaseName){
        println("createDatabase 호출됨");

        try { //try문 하면 더 안정된 코드로 만들 수 ㅇ
            database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null); //데이터베이스 생성하기
            println("데이터베이스 생성됨: " + databaseName);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void createTable(String tableName){
        println("createTable 호출됨");

        if(database==null){
            println("데이터베이스를 먼저 열어주세요.");
            return;
        }

        try {
            String sql = "create table if not exists " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
            database.execSQL(sql);
            println("테이블 생성됨: " + tableName);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void insertRecord(){
        println("insertRecord 호출됨");

        if(database==null){
            println("데이터베이스를 먼저 열어주세요");
            return;
        }

        String tableName=editText2.getText().toString();
        if(tableName==null){
            println("테이블 이름을 입력하세요");
            return;
        }

        try {
            String sql = "insert into " + tableName + " (name,age,mobile) values ('ayoung',21,'010-33**-66**')";
            database.execSQL(sql);
            println("레코드 추가함");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void executeQuery() {
        println("executeQuery 호출됨");
        try {
            if (database == null) {
                println("데이터베이스를 먼저 열어주세요");
                return;
            }
            String tableName = editText2.getText().toString();
            if (tableName == null) {
                println("테이블 이름을 입력하세요");
                return;
            }
            String sql = "select _id,name, age, mobile from " + tableName;
            Cursor cursor = database.rawQuery(sql, null); //데이터베이스를 조회할때는 rawQuery메서드 호출
            //레코드를 하나씩 넘겨주는게 Cursor
            int recordCount = cursor.getCount(); //레코드 개수
            println("레코드 개수: " + recordCount);

            for (int i = 0; i < recordCount; i++) {
                cursor.moveToNext();

                int id = cursor.getInt(0); //첫번째 column
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String mobile = cursor.getString(3);

                println("레코드 #" + i + ": " + id + ", " + name + ", " + age + ", " + mobile);
            }

            cursor.close(); //커서도 닫아조야함
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void println(String data){
        textView.append(data+"\n");
    }
}