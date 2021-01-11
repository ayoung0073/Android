package com.moonayoung.sliding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Animation translateLeftAnim;
    Animation translateRightAnim;

    LinearLayout page;
    Button button;

    boolean isPageOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page=findViewById(R.id.page);


        translateLeftAnim= AnimationUtils.loadAnimation(this,R.anim.translate_left); // 애니메이션 로드
        translateRightAnim=AnimationUtils.loadAnimation(this,R.anim.translate_right);

        SlidingAnimationListener animListener=new SlidingAnimationListener(); //밑에서 정의한 클래스를 이용해 animListener 선언
        translateLeftAnim.setAnimationListener(animListener); // translateLeftAnim에 animListener 구현
        translateRightAnim.setAnimationListener(animListener); // 얘도

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPageOpen){ //페이지가 열려있으면
                    page.startAnimation(translateRightAnim); //페이지를 오른쪽으로 슬라이딩
                }
                else { //닫혀있으면
                    page.setVisibility(View.VISIBLE); //페이지를 보이게 하고
                    page.startAnimation(translateLeftAnim); //왼쪽으로 슬라이딩하는 애니메이션 보여줌
                }
            }
        });
    }

    class SlidingAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) { // 애니메이션이 끝날 때
            if(isPageOpen){ // 페이지가 열려있을 때 오른쪽으로 슬라이딩하고 난 후
                page.setVisibility(View.INVISIBLE); // 페이지를 보이지 않게 하고
                button.setText("열기"); // 버튼의 text를 열기로 고침
                isPageOpen=false; // 페이지 닫혀있는 걸로 바뀜
            }else{ // 페이지가 닫혀있어서 왼쪽으로 슬라이딩해서 보이게 됐을 때
                button.setText("닫기");
                isPageOpen=true; // 열려있는 걸로 바뀜
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}