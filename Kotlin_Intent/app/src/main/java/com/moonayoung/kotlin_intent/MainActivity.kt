package com.moonayoung.kotlin_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { //람다식 이용
            var intent = Intent(this,MenuActivity::class.java)
            startActivityForResult(intent,101)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(this,"메인화면으로 돌아옴",Toast.LENGTH_LONG).show();

        if(requestCode==101){
            if(resultCode == RESULT_OK){
                var name = data?.getStringExtra("name") //?붙임
                Toast.makeText(this,"전달받은 이름: "+name,Toast.LENGTH_LONG).show()

            }
        }
    }
}