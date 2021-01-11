package com.moonayoung.kotlin_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main,container,false);
        var button : Button = rootView.findViewById(R.id.button) //rootView에서 찾기....
        button.setOnClickListener {

            //MainActivity().onFragmentChanged(1)
            //activity.onFragmentChanged(1)
            (activity as MainActivity).onFragmentChanged(1)
            // 그냥 activity하면 fragmentActivity로 인식됨
        }
        return rootView
    }

}