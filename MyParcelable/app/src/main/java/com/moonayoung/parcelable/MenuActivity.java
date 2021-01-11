package com.moonayoung.parcelable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent=getIntent();
        processIntent(intent);
    }

    public void processIntent(Intent intent){
        if(intent!=null){
            Bundle bundle=intent.getExtras();
            SimpleData data=bundle.getParcelable("data");

            if(data!=null){
                Toast.makeText(this,"전달받은 객체:"+data.code+", "+data.msg,Toast.LENGTH_LONG).show();
            }
        }
    }


}

