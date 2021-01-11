package com.moonayoung.kotlin_fragment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {

    val menuFragment = MenuFragment();
    val mainFragment = MainFragment();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun onFragmentChanged(num: Int): Unit {
        if (num == 0) {
            supportFragmentManager.beginTransaction().replace(R.id.container, mainFragment).commit();
            // 자바에서는 getSupportFragmentManager()호출해서 사용했지만
            // 코틀린에서는 supportFragmentManager 변수처럼 사용
            // Fragment 클래스에서도 getActivity가 아닌 activity 변수로 사용 ㅇ
            // MainFragment 객체 생성할 때 new를 쓰지 않고 바로 MainFragment()로
            // container가 아닌 R.id.container로 쓰는 이유는 정수값(id)로 반환하기 때문

            // 처음에 메인액티비티 레이아웃에 id를 mainFragment로 지정한 프래그먼트를 입력했다가, 널포인터가 생겨서
            // 여기서 만든 메인프래그먼트 객체를 넣음
        } else if (num == 1) {
            supportFragmentManager.beginTransaction().replace(R.id.container, menuFragment).commit();

        }
    }
}