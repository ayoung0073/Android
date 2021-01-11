package com.moonayoung.restful;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button diaryBT = findViewById(R.id.diary);
        final Button diary_write = findViewById(R.id.diary_write);

        diaryBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final GetDiarysFragment diarysFragment = new GetDiarysFragment();

                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.container, diarysFragment).commit();

                diary_write.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PostFragment postFragment = new PostFragment();

                        FragmentManager fm = getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.container, postFragment).commit();

                    }
                });
            }
        });
    }
}



