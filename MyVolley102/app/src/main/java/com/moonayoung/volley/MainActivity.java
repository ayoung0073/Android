package com.moonayoung.volley;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;
        import com.google.gson.Gson;

        import java.util.HashMap;
        import java.util.Map;

//불리를 이용해 서버로 요청하고, 응답을 받아 그 응답을 gson을 이용해 Json포맷으로 온 응답을 파싱함.

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    //핸들러 안씀, 불리라고 하는 게 쓰레드를 안하는거 내부에서 핸들러까지 다 처리

    static RequestQueue requestQueue; //요청을 보낼 때 요청 객체를 넣어줄 수 있는 Queue

    RecyclerView recyclerView;
    MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        editText.setText("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101");
        textView=findViewById(R.id.textView);

        recyclerView =findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        //리싸이클러뷰에 레이아웃 설정

        adapter=new MovieAdapter();
        recyclerView.setAdapter(adapter);
        //리싸이클러뷰에 어댑터 설정


        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlStr=editText.getText().toString();
                request(urlStr);
            }
        });

        if(requestQueue==null) { //널 에러나서 추가해봄
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void request(String urlStr){ //volley는 요청객체를 하나 선언 그 안에서 핸들러 처리함함

        StringRequest request=new StringRequest( //여기서 실행되는 쓰레드는 우리가 만든 것이 아니라 불리에서 만들어진 것
                Request.Method.GET, //Get방식으로
                urlStr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> "+response);

                        processResponse(response); //밑에 메서드 정의함. 영화정보의 수 출력하는 메서드

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> "+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<String, String>();

                return params;
            }
        };

        request.setShouldCache(false); // 캐싱을 하는것, 요청 보낼 때마다 새롭게 보내는 게 아니라 기존꺼 보낼 수 있게 // 이거는 false
        requestQueue.add(request); // 큐에 요청 객체 추가
        println("요청 보냄");
    }

    public void processResponse(String response){
        Gson gson=new Gson(); //Gson 객체 생성

        MovieList movieList=gson.fromJson(response,MovieList.class);
        //gson이 클래스 정보를 보고 자동으로 객체를 만들어줌
        println("영화 정보의 수: " + movieList.boxOfficeResult.dailyBoxOfficeList.size());

        //어댑터 데이터 추가하기
        for(int i=0;i<movieList.boxOfficeResult.dailyBoxOfficeList.size();i++) {
            Movie movie = movieList.boxOfficeResult.dailyBoxOfficeList.get(i);
            adapter.addItem(movie);
        }

        adapter.notifyDataSetChanged(); //어댑터에서 관리하는 게 바뀔 때 recyclerView도 바꿀 수 있게 알림
    }
    public void println(String data){
        textView.append(data+"\n");
    }

}