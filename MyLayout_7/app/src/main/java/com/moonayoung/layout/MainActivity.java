package com.moonayoung.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Layout1 layout1;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1=findViewById(R.id.layout1);
        layout1.setImage(R.drawable.ic_launcher_foreground);
        layout1.setName("무나영");
        layout1.setMobile("010-33**-66**");

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setImage(R.drawable.me);
            }
        });
    }
}