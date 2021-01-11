package com.moonayoung.hellokotlin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            Toast.makeText(this, "확인1 버튼 눌림", Toast.LENGTH_LONG).show();
            button.text = "버튼 클릭됨";
        }

        button2.setOnClickListener{
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
            startActivity(intent);
            button2.text = "버튼 클릭됨";
        }

        //  public void onButton3Clicked(View v){
        //    Intent intent = new Intent(Intent.ACTION_VIEW,)
        //}
    }
}