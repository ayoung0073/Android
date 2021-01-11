package com.moonayoung.kotlin_intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*;

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        button2.setOnClickListener {
            intent = Intent()
            intent.putExtra("name", "ayoung")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
