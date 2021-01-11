package com.moonayoung.kotlin_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view.setOnTouchListener { view: View, event: MotionEvent ->
            var eventInt = event.action

            if (eventInt == MotionEvent.ACTION_DOWN) {
                textView.append("손가락 눌림: " + event.getX() + ", " + event.getY() + "\n")
            }
            //return 없이 반환값 입력
            true

        }


        class Detector : GestureDetector.OnGestureListener {
            // 내부클래스로 생성 -> textView를 참조 가능

            override fun onDown(p0: MotionEvent?): Boolean {
                textView.append("onDown 호출됨\n")
                return true // return과 함께 반환값 씀
            }

            override fun onFling(
                p0: MotionEvent?,
                p1: MotionEvent?,
                p2: Float,
                p3: Float
            ): Boolean {
                textView.append("onFling 호출됨\n")
                return true
            }

            override fun onLongPress(p0: MotionEvent?) {
                textView.append("onLongPress 호출됨\n")
                // 원래 있던 To do 함수를 사용하면 실행 도중 에러 남
            }

            override fun onScroll(
                p0: MotionEvent?,
                p1: MotionEvent?,
                p2: Float,
                p3: Float
            ): Boolean {
                textView.append("onScroll 호출됨: "+p2 + ", " + p3 +"\n")
                return true
            }

            override fun onShowPress(p0: MotionEvent?) {
                textView.append("onShowPress 호출됨\n")
            }

            override fun onSingleTapUp(p0: MotionEvent?): Boolean {
                textView.append("onSingleTapUp 호출됨\n")
                return true
            }
        }

        var listener = Detector() //내부에서 Detector 클래스 생성 후, 객체 생성
        var detector = GestureDetector(this, listener)
        view2.setOnTouchListener { view:View, motionEvent : MotionEvent ->
            return@setOnTouchListener detector.onTouchEvent(motionEvent)
        }
    }
}
