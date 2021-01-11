package com.moonayoung.customviewimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewImage extends View {

    Paint paint;
    Bitmap cacheBitmap;
    Canvas cacheCanvas;

    public CustomViewImage(Context context) {
        super(context);

        init(context);
    }

    public CustomViewImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context){
        paint=new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { //뷰의 크기가 변경될 떄 호출됨, 뷰가 차지하는 영역이 결정되는 시점에 미리 정함
        super.onSizeChanged(w, h, oldw, oldh);

        createCacheBitmap(w,h);
        testDrawing();
    }

    public void createCacheBitmap(int w, int h){
        cacheBitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        cacheCanvas=new Canvas(); //메모리에 미리 뭔가 그려주는 과정해주기, 화면에 보이는 시점에 그 객체를 뿌려줌.
        cacheCanvas.setBitmap(cacheBitmap);
    }

    public void testDrawing(){
        Bitmap srcImg=BitmapFactory.decodeResource(getResources(),R.drawable.tailand3);//Resource: Res밑에 drawble폴더에 잇는 것을 바로 로딩해줌
        cacheCanvas.drawBitmap(srcImg,100,100,paint);

        //Matrix를 이용해 이미지변환 //행렬 계산
        Matrix matrix=new Matrix();
        matrix.setScale(1,-1); //확대축소 관련..이건 상하방향으로 바뀜, 일반적으로 많이 하지않음 api 참조하기
        Bitmap inverseBitmap=Bitmap.createBitmap(srcImg,0,0,srcImg.getWidth(),srcImg.getHeight(),matrix,false);
        cacheCanvas.drawBitmap(inverseBitmap,300,300,paint);//메모리에 만들어진 비트맵객체에 draw 하겠다


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

      /*  Bitmap srcImg=BitmapFactory.decodeResource(getResources(),R.drawable.tailand);//Resource: Res밑에 drawble폴더에 잇는 것을 바로 로딩해줌
        canvas.drawBitmap(srcImg,100,100,paint);*/

      if(cacheCanvas!=null){
          canvas.drawBitmap(cacheBitmap,0,0,null);
      }


    }
}

