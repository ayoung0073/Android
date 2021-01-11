package com.moonayoung.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        webView=findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString()); //build.gradle(Module:app)으로 가서 minSdkVersion을 21로 고치고->sync now 해야 getUrl()이 에러 안 뜸
                return true;
            }
        });

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(editText.getText().toString()); //AndroidManifest에 인터넷 권한 추가해야함!
                //    <uses-permission android:name="android.permission.INTERNET" /> 추가
                //    <application
                //        android:usesCleartextTraffic="true" -> application 태그안에 속성 추가
            }
        });
    }
}