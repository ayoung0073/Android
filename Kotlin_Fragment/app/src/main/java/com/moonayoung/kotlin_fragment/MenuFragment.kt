package com.moonayoung.kotlin_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_menu,container,false)
        var button2 : Button = rootView.findViewById(R.id.button2)
        button2.setOnClickListener {
            // rootView하고 바로 button2 클릭리스너하면 널포인터. findViewById 해야함
            // 안하면 NPE...

            //MainActivity().onFragmentChanged(0)
            (activity as MainActivity).onFragmentChanged(0)
        }
        return rootView
    }
}