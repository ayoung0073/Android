package com.moonayoung.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textView;

    static RequestQueue requestQueue;
    //요청큐는 한번만 만들어 계속 사용할 수 있기 때문에 클래스 변수로 선언
    //요청큐는 앱 전체에서 사용하는 것이 일반적

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });

        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(getApplicationContext());
            // RequestQueue 생성
        }
    }

    public  void makeRequest(){
        String url = editText.getText().toString();

        StringRequest request = new StringRequest(Request.Method.GET, //요청을 보내기 위한 객체 만들기
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
                ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };

        request.setShouldCache(false); //cache를 사용하지 않게 함
        requestQueue.add(request);
        println("요청 보냄 :" + request.getBodyContentType());
    }

    public void println(String str){
        textView.append(str + "\n");
    }
}
