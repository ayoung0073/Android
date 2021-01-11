package com.moonayoung.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {//Helper는 실제 앱을 사용하는 사용자 단말에 데이터베이스 파일이 기존에 있냐없냐 구분함 그래서 저 두개 메서드 implement해야함
    private static final String DATABASE_NAME="person.db";
    private static final int DATABASE_VERSION=1;

    public static final String TABLE_NAME="person";
    public static final String PERSON_ID="_id"; //칼럼명
    public static final String PERSON_NAME="name";
    public static final String PERSON_AGE="age";
    public static final String PERSON_MOBILE="mobile";

    public static final String[] ALL_COLUMNS={PERSON_ID,PERSON_NAME,PERSON_AGE,PERSON_MOBILE}; //배열객체만들기

    private static final String CREATE_TABLE= //띄어쓰기랑 , 중요
            "create table "+TABLE_NAME+ " ("+
                    PERSON_ID+" integer primary key autoincrement, "+
                    PERSON_NAME+" text,"+
                    PERSON_AGE+" integer, "+
                    PERSON_MOBILE +" text"+
                    ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); //context객체만 넘겨주면 데이터베이스만들어질거임
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //기존 데이터베이스가 없는 경우에
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME); //여기서 그냥 없애지만 실제로는 데이터 날리면 안댐 테스트할 때만 기존에 있던 거 없애고 새로 ㅁ나듬
        onCreate(sqLiteDatabase); //
    }


}
