package com.moonayoung.cumstomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CustomView extends View {
    Paint paint;

    public CustomView(Context context) { //생성자 추가 (파라미터 1개)
        super(context);

        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) { //생성자 추가 (파라미터 2개)
        super(context, attrs);

        init(context); //초기화를 위해 사용될 메서드, 두 개의 생성자에서 똑같이 호출
    }

    public void init(Context context){ //Paint 객체 초기화
        paint=new Paint();
        paint.setColor(Color.LTGRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) { //onDraw 메서드 오버라이딩한 후, 원하는 그래픽 그리기 //파라미터: Canvas 객체
        super.onDraw(canvas);

        //canvas.drawRect(100,100,200,200,paint);

        paint.setStyle(Paint.Style.FILL); //테두리선:stroke, 안에 채우는 것: fill
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(10,10,300,300,paint); //dimension 하면 단말해상도에 따라 다른 픽셀값지정됨(예전에 배움)

        paint.setStyle(Paint.Style.STROKE); //FILL_AND_STROKE는 테두리선과 안에까지 다채워주는 거임
        paint.setStrokeWidth(10.0F);
        paint.setColor(Color.DKGRAY); //아직 그리진 않음 canvas 객체에 draw메서드를 해야함
        canvas.drawRect(10,10,300,300,paint);

        paint.setAntiAlias(true); //사각형을 구분안되고 부드럽게 그리게
        canvas.drawCircle(500,500,200,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            Toast.makeText(getContext(),"눌렸음 : "+event.getX()+", "+event.getY(),Toast.LENGTH_LONG).show();
            // 터치한 위치 토스트로 띄움
        }

        return super.onTouchEvent(event);
    }
}
