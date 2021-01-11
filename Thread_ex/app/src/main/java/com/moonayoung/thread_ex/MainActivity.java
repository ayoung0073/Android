package com.moonayoung.thread_ex;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView countText;
    EditText input;
    LinearLayout layout_words;
    Button button;
    Handler handler = new Handler();
    int number = 0;

    TextView[] textViews = null;
    TextView answer_count;
    CountThread thread;

    boolean stop;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        layout_words = findViewById(R.id.layout_words);
        countText = findViewById(R.id.countText);
        input = findViewById(R.id.input);
        answer_count = findViewById(R.id.answer_count);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread = new CountThread(); // 시간 count하는 쓰레드 생성
                thread.start();
            }
        });

        final String[] words={"java", "android", "jsp", "python", "virus", "data","spring","django","web","mysql","database","nodejs","structure","security"};
        textViews = new TextView[words.length];

        for (int i = 0; i < words.length; i++) { // 텍스트뷰 객체 추가
            textViews[i] = new TextView(this);
            textViews[i].setTextSize(24);
            textViews[i].setText(words[i]);
            textViews[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViews[i].setBackgroundResource(R.drawable.bg_words);
            textViews[i].setPadding(0,20,0,20);
            layout_words.addView(textViews[i]);
        }


        input.setOnEditorActionListener(new TextView.OnEditorActionListener() { // 엔터키 눌렀을 때
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (stop == true) { // 실패 이후에도 입력했을 때 막음
                    Toast.makeText(getApplicationContext(), "게임이 종료되었습니다.", Toast.LENGTH_LONG).show();
                    input.setText("");
                } else {
                    if ((textViews[number].getText().toString()).equals(input.getText().toString())) {
                        textViews[number].setText("");
                        number = number + 1;
                        answer_count.setText(number + "개");

                        if (number == words.length) { // 맞힌 개수가 words 배열 개수와 같으면 성공
                            Toast.makeText(getApplicationContext(), "성공!", Toast.LENGTH_LONG).show();
                            setStop(true);
                        }
                    }
                    input.setText("");
                }
                return true;
            }
        });
    }

    class CountThread extends Thread {
        int count = 0;
        public void run() {
            for (count = 60; count >= 0; count--) { // 60초로 지정
                if(stop==true) break; // 60초 내에 성공했을 때, for문을 빠져나가게 함
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }



                handler.post(new Runnable() {
                    @Override
                    public void run() {
                            countText.setText(Integer.toString(count)); // 스레드에서 메인스레드에서 관리하는 UI객체를 접근할 때는 핸들러를 이용해야 함.
                    }
                });
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(stop==true){ // 메인스레드에서 관리하는 UI 객체에 접근하기 때문에 handler 이용
                        countText.setText("수고하셨습니당 ^_^");
                    }
                    else {
                        countText.setText("실패!");
                        setStop(true); // 실패한 후에도, 입력시 정답이어도 처리가 안되게 하기 위해 setStop(true) 지정
                    }
                }
            });

        }
    }

    public void setStop(boolean stop){
        this.stop = stop;
    }
}