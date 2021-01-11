package com.moonayoung.restful;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_post, container,false);
        Button button = rootView.findViewById(R.id.writeBT);
        final EditText dateE = rootView.findViewById(R.id.date);
        final EditText writerE = rootView.findViewById(R.id.writer);
        final EditText titleE = rootView.findViewById(R.id.title);
        final EditText contentE = rootView.findViewById(R.id.content);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = dateE.getText().toString();
                String writer = writerE.getText().toString();
                String title = titleE.getText().toString();
                String content = contentE.getText().toString();

                final Diary diary = new Diary();
                diary.setDate(date);
                diary.setContent(content);
                diary.setTitle(title);
                diary.setWriter(writer);

                final GetDiaryFragment fragment = new GetDiaryFragment();

                RetrofitClient.getApiService().postDiary(diary).enqueue(new Callback<Diary>() {
                    @Override
                    public void onResponse(Call<Diary> call, Response<Diary> response) {
                        Log.d("통신", "onResponse: "+response.toString());
                        Log.d("통신", "onResponse: "+response.body());

                        String str="";
                        Diary getDiary = response.body();
                        FragmentManager fm = getFragmentManager();
                        Bundle bundle = new Bundle();
                        bundle.putString("getDate",getDiary.date);
                        bundle.putString("getWriter",getDiary.writer);
                        bundle.putString("getTitle",getDiary.title);
                        bundle.putString("getContent",getDiary.content);

                        fragment.setArguments(bundle);
                        fm.beginTransaction().replace(R.id.container, fragment).commit();

                    }

                    @Override
                    public void onFailure(Call<Diary> call, Throwable t) {
                        Log.d("통신", "failed");
                    }
                });

            }
        });

        return rootView;
    }
}
