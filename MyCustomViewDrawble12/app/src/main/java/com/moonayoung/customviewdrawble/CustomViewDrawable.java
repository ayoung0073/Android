package com.moonayoung.customviewdrawble;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class CustomViewDrawable extends View {

    ShapeDrawable upperDrawble;//사각형이나 원을 그려줄 수 있는 객체
    ShapeDrawable lowerDrawble;

    public CustomViewDrawable(Context context) {
        super(context);

        init(context);
    }

    public CustomViewDrawable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context){
        WindowManager manager=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display=manager.getDefaultDisplay();
        // 윈도우매니저 역할
        int displayWidth=display.getWidth();
        int displayHeight=display.getHeight(); // 윈도우 매니저를 이요해 뷰의 폭과 높이 확인

        Resources resources=getResources();
        // 우리가 컬러 정하는 거 // 리소스에 정의된 색상 값을 Resources 객체를 통해 변수에 설정
        int blackColor=resources.getColor(R.color.color01);
        int grayColor=resources.getColor(R.color.color02);
        int darkGrayColor=resources.getColor(R.color.color03);

        upperDrawble=new ShapeDrawable(); //Drawable 객체 생성

        RectShape rectangle=new RectShape();
        rectangle.resize(displayWidth, displayHeight*2/3);
        upperDrawble.setShape(rectangle);
        upperDrawble.setBounds(0,0,displayWidth,displayHeight*2/3);

        LinearGradient gradient=new LinearGradient(0,0,0,displayHeight*2/3,grayColor,darkGrayColor, Shader.TileMode.CLAMP);
        //LinearGradient 객체 생성, 말그대로 선으로 그려지는 그라데이션
        //뷰의 높이의 2/3만큼 설정 grayColor 에서 darkGrayColor로

        Paint paint=upperDrawble.getPaint();
        paint.setShader(gradient); //Paint객체에 새로 생성한 LinearGradient 객체를 Shader로 설정

        lowerDrawble=new ShapeDrawable();

        RectShape rectangle2=new RectShape();
        rectangle2.resize(displayWidth,displayHeight*1/3);
        lowerDrawble.setShape(rectangle2);
        lowerDrawble.setBounds(0,displayHeight*2/3,displayWidth,displayHeight);//크기 설정

        LinearGradient gradient2=new LinearGradient(0,0,0,displayHeight*1/3,darkGrayColor,blackColor, Shader.TileMode.CLAMP);
        //뷰의 높이의 2/3만큼 설정 darkGrayColor 에서 blackColor로

        Paint paint2=lowerDrawble.getPaint();
        paint2.setShader(gradient2);
    }

    @Override
    protected void onDraw(Canvas canvas) { //그래픽을 그릴 수 있게 만드는 메소드
        super.onDraw(canvas);

        upperDrawble.draw(canvas);
        lowerDrawble.draw(canvas);
    }
}
