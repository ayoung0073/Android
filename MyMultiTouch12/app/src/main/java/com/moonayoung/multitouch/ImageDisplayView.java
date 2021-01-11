package com.moonayoung.multitouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ImageDisplayView extends View {
    private static final String TAG="ImageDisplay";

    Paint paint;
    Matrix matrix; // 이미지의 크기를 확대/축소/이미지 이동하기 위해 사용할 수 있는 쉬운 방법은 매트릭스 객체 사용하는 것

    int lastX; // 이전 좌표값을 담아둘 변수 선언
    int lastY;

    Canvas mCanvas;
    Bitmap mBitmap;

    float displayWidth=0.0F;
    float displayHeight=0.0F;

    int displayCenterX=0;
    int displayCenterY=0;

    public float startX;
    public float startY;

    float oldDistance=0.0F;

    int oldPointerCount=0;
    boolean isScrolling=false;
    float distanceThreshold=3.0F;

    float scaleRatio;
    float totalScaleRatio;

    Bitmap img;

    public static float MAX_SCALE_RATIO=5.0F;
    public static float MIN_SCALE_RATIO=0.1F;

    public ImageDisplayView(Context context) {
        super(context);

        init(context);
    }

    public ImageDisplayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context){
        paint=new Paint();
        matrix=new Matrix();

        lastX=-1;
        lastY=-1;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(w>0 && h>0){
            newImage(w,h);
        }
    }

    public void newImage(int w, int h){
        img= Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas();
        canvas.setBitmap(img);

        mBitmap=img;
        mCanvas=canvas;

        displayWidth=(float)w;
        displayHeight=(float)h;

        displayCenterX=w/2;
        displayCenterY=h/2;
    }

    public void setImageData(Bitmap bitmap){

        Canvas canvas=new Canvas();
        //canvas.setBitmap(bitmap);

        mBitmap = bitmap;
        mCanvas=canvas;

        int w = bitmap.getWidth();
        int h= bitmap.getHeight();

        displayWidth=(float)w;
        displayHeight=(float)h;

        displayCenterX=w/2;
        displayCenterY=h/2;

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mBitmap!=null){
            canvas.drawBitmap(mBitmap,0,0,null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action=event.getAction();

        int pointerCount= event.getPointerCount(); //손가락의 개수 반환
        Log.d(TAG, "Pointer Count: "+pointerCount);

        switch(action){
            case MotionEvent.ACTION_DOWN: // 손가락으로 눌렀을 때의 기능 추가

                if(pointerCount==1){ //손가락이 하나면 이미지 이동
                    float curX=event.getX();
                    float curY=event.getY();

                    startX=curX;
                    startY=curY;
                }
                else if(pointerCount==2) //손가락이 두개면 손가락 사이의 거리값에 의해 확대 or 축소하기
                {
                    oldDistance=0.0F;

                    isScrolling=true;
                }
                return true;

                case MotionEvent.ACTION_MOVE: // 손가락으로 움직일 때의 기능 추가

                    if(pointerCount==1){
                        if(isScrolling){
                            return true;
                        }

                        float curX=event.getX();
                        float curY=event.getY();

                        if(startX==0.0F){
                            startX=curX;
                            startY=curY;

                            return true;
                        }

                        float offsetX=startX-curX;
                        float offsetY=startY-curY;

                        if(oldPointerCount==2){

                        } else {
                            Log.d(TAG, "ACTION_MOVE: "+offsetX+", "+offsetY);

                            if(totalScaleRatio>1.0F){ //손가락을 완전 민감하겐 안하도록
                                moveImage(-offsetX, -offsetY); // 한 손가락으로 움직이고 있을 때는 moveImage() 메서드 호출
                            }

                            startX=curX;
                            startY=curY;
                        }
                    }

                    else if(pointerCount==2)
                    {
                        float x1=event.getX(0);
                        float y1=event.getY(0);
                        float x2=event.getX(1);
                        float y2=event.getY(1);

                        float dx=x1-x2;
                        float dy=y1-y2;
                        float distance=new Float(Math.sqrt(new Float(dx*dx+dy*dy)));

                        float outScaleRatio=0.0F;
                        if(oldDistance == 0.0F){
                            oldDistance=distance;

                            break;
                        }

                        if(distance>oldDistance){
                            if((distance-oldDistance)<distanceThreshold){
                                return true;
                            }
                            outScaleRatio=scaleRatio-(distance/oldDistance*0.05F);
                        }
                        if(outScaleRatio<MIN_SCALE_RATIO || outScaleRatio>MIN_SCALE_RATIO){
                            Log.d(TAG, "Invalid scaleRatio: "+ outScaleRatio);
                        }else{
                            Log.d(TAG, "Distance: "+distance+", ScaleRatio: "+ outScaleRatio);
                            scaleImage(outScaleRatio);
                        }

                    }
        }
        return super.onTouchEvent(event);

    }

    public void moveImage(float offsetX, float offsetY){
        matrix.postTranslate(offsetX,offsetY);

        invalidate(); //
    }

    public void scaleImage(float inScaleRatio){
        matrix.postScale(inScaleRatio,inScaleRatio,displayCenterX,displayCenterY);
        matrix.postRotate(0);

        totalScaleRatio=totalScaleRatio*inScaleRatio;
        invalidate();
    }

}
