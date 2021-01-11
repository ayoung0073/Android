package com.moonayoung.challenge13;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UrlFragment extends Fragment {

    Button homeBT;
    WebView webView;
    String url=null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_url, container, false);

        homeBT=rootView.findViewById(R.id.homeBT2);

        Bundle bundle=getArguments();
        //Intent intent = getActivity().getIntent();
        final String type = bundle.getString("type");


        if(type.equals("blog")) url= "http://m.blog.naver.com/ayong0310";
        else if(type.equals("insta")) url="http://www.instagram.com/__a_young";
        else if(type.equals("study_webHacking")) url="https://blog.naver.com/ayong0310/222066799764";
        else if(type.equals("study_network")) url="https://blog.naver.com/ayong0310/222059999019";


        webView = rootView.findViewById(R.id.webView);


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

        webView.loadUrl(url);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        homeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MenuActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }


}
