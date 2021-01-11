package com.moonayoung.restful;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDiarysFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_get_diarys, container, false);
        final TextView diarys_text = rootView.findViewById(R.id.diarys);

        final Call<ArrayList<Diary>> diarys = RetrofitClient.getApiService().getDiarys();
        diarys.enqueue(new Callback<ArrayList<Diary>>() {
            @Override
            public void onResponse(Call<ArrayList<Diary>> call, Response<ArrayList<Diary>> response) {
                ArrayList<Diary> diaryList = response.body();
                Log.d("통신", "onResponse: " + response.toString());
                String str = "";
                Log.d("내용", "" + diaryList.size());

                for (int i = 0; i < diaryList.size(); i++) {
                    if (diaryList.get(i).getWriter().equals("ayoung")) {
                        str += diaryList.get(i).getDate() + "\n";
                        str += diaryList.get(i).getTitle() + "\n";
                        str += diaryList.get(i).getContent() + "\n";
                        str += "\n\n";
                    }
                }
                Log.d("내용", "" + str);
                str = str.replaceAll("<p>", "");
                str = str.replaceAll("</p>", "");

                diarys_text.setText(str);
            }

            @Override
            public void onFailure(Call<ArrayList<Diary>> call, Throwable t) {
                Log.d("통신", "실패");
                Log.d("통신", "" + t.getMessage());
            }
        });
        return rootView;
    }
}
