package com.moonayoung.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class MyButton extends AppCompatButton {

    public MyButton(Context context) { //버튼이니까 버튼을 담은 context를 상속받아야함. (생성자 필수) new Button으로 해야 직접 메모리에 생성됨 xml에 하는건 메모리가 거기서 생성.
        super(context);

        init(context); //코드가 중복되지 않도록 공동으로 호출
    } //이 필수 생성자는 소스코드에서 호출된 것.

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    } //xml레이아웃에서 들어가서 inflation돼서 메모리가 객체화된것...?

    public void init(Context context){
        setBackgroundColor(Color.LTGRAY);
        setTextColor(Color.BLACK);

        float textSize=getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize); //sp단위가 아니라 픽셀 단위임. 단말의 해상도에 따라 글자크기가 달라져서 sp로 하는데 여기서는 픽셀단위함
    }

    //버튼의 크기 결정. size return해서..


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("MyButton","onDraw 호출됨");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        Log.d("MyButton","onTouchEvent 호출됨");

        int action=event.getAction();
        switch(action)
        {
            case MotionEvent.ACTION_DOWN: //손가락이 눌렸을 때
                setBackgroundColor(Color.WHITE);
                setTextColor(Color.RED);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                setBackgroundColor(Color.LTGRAY); //원래대로
                setTextColor(Color.BLACK);
                break;
        }
        invalidate(); //화면에 보이는 게 유효하지 않다는 거 그러면 reDraw함 그러면 onDraw함수도 같이 호출함.

        return true;
    }
}
