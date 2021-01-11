package com.moonayoung.restful;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GetDiaryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_get_diary, container, false);
        TextView date = rootView.findViewById(R.id.getDate);
        TextView writer = rootView.findViewById(R.id.getWriter);
        TextView title = rootView.findViewById(R.id.getTitle);
        TextView content = rootView.findViewById(R.id.getContent);

        String getDate = getArguments().getString("getDate");
        String getWriter = getArguments().getString("getWriter");
        String getTitle = getArguments().getString("getTitle");
        String getContent = getArguments().getString("getContent");

        date.setText(getDate);
        writer.setText("작성자 " + getWriter);
        title.setText("제목 "+getTitle);
        content.setText("내용\n" + getContent);



        return rootView;
    }
}
