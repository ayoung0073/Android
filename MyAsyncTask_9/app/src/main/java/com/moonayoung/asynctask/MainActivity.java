package com.moonayoung.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

    int value; //progressBar 값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.progressBar2);

        final BackgroundTask task=new BackgroundTask();

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.execute();
            }
        });

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.cancel(true);
            }
        });
    }

    class BackgroundTask extends AsyncTask<Integer,Integer,Integer>{
        @Override
        protected void onPreExecute() { //초기화 작업에 사용, 메인 스레드에서 실행
            value=0;
            progressBar.setProgress(value); // 메인 스레드의 UI 객체 직접 접근 가능
        }

        @Override
        protected void onPostExecute(Integer integer) { //doInBackground 작업이 끝난 후 return 값이 여기로 넘어옴
            progressBar.setProgress(0);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0].intValue());
        }

        @Override
        protected void onCancelled() {
            progressBar.setProgress(0);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            while(isCancelled()==false){
                value+=1;

                if(value>=100) break;

                publishProgress(value); //publishProgress 호출하면 onProgressUpdate 메서드 호출됨

                try{
                    Thread.sleep(1000);
                }catch(Exception e){}
            }
            if(isCancelled()==true) {
                value=0;
                publishProgress(value);
            }
            return value;
        }
    }
}