package com.moonayoung.paintboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintBoard extends View {

    Paint paint;
    Canvas mCanvas; //메모리에 만들 캔버스
    Bitmap mBitmap; //메모리 비트맵

    int lastX; //이전 좌표를 담아둘 변수
    int lastY; //이전 y좌표

    public PaintBoard(Context context) { // 생성자 추가
        super(context);

        init(context);
    }

    public PaintBoard(Context context, @Nullable AttributeSet attrs) { // 생성자 추가
        super(context, attrs);

        init(context); // Paint 객체 초기화하는 메서드 호출
    }

    public void init(Context context){
        paint=new Paint();

        paint.setColor(Color.BLACK);

        lastX = -1;
        lastY = -1;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888); //비트맵 만들기
        mCanvas=new Canvas();
        mCanvas.setBitmap(mBitmap);

        mCanvas.drawColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mBitmap!=null)
        {
            canvas.drawBitmap(mBitmap,0,0,null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action=event.getAction();

        int X=(int) event.getX();
        int Y=(int) event.getY();

        switch(action){
            case MotionEvent.ACTION_UP:
                lastX=-1;
                lastY=-1;

                break;

            case MotionEvent.ACTION_DOWN:
                if(lastX!=-1){
                    if(X!=lastX || Y!=lastY){
                        mCanvas.drawLine(lastX, lastY, X, Y, paint);
                    }
                }

                lastX=X;
                lastY=Y;

                break;

            case MotionEvent.ACTION_MOVE:
                if(lastX!=-1){
                    mCanvas.drawLine(lastX, lastY, X, Y, paint);
                }

                lastX=X;
                lastY=Y;

                break;
        }

        invalidate(); //화면에 보여지게 하는 것. 화면에 보여준 게 유효하지 않아 다시 그리게 만드는 메서드

        return true;
    }
}
