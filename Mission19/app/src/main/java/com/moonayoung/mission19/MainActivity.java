package com.moonayoung.mission19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView dataText;
    WebView webView;

    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        dataText = findViewById(R.id.dataText);
        webView = findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                //build.gradle(Module:app) -> minSdkVersion 21로 고치기
                return true;
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText!=null) {
                    final String url = editText.getText().toString();

                    webView.loadUrl(url);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            request(url);
                        }
                    }).start();


                }
            }
        });
    }

    public void request(String urlStr){
        //자바는 HttpUrlConnection 기본적으로 씀
        try{
            StringBuilder builer = new StringBuilder();
            //StringBuilder객체는 String을 붙여가면서 쓸 수 있음
            URL url = new URL(urlStr);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            if(con != null)
            {
                con.setConnectTimeout(10000);
                con.setRequestMethod("GET");
                con.setDoInput(true);

                int responseCode = con.getResponseCode();

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = null;

                while(true){
                    line = reader.readLine();
                    if(line == null){
                        break;
                    }
                    builer.append(line+"\n");

                }
                reader.close(); //데이터입력 읽는 것 끊기
                con.disconnect(); //연결 끊기
            }
            println("응답\n\n"+builer.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void println(final String str){
        handler.post(new Runnable() {
            @Override
            public void run() {
                dataText.append(str);
            }
        });
    }
}