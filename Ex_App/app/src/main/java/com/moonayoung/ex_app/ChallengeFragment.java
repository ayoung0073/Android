package com.moonayoung.ex_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChallengeFragment extends Fragment {
    ChallengeAdapter adapter;
    Button registerBT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_challenge,container,false);
        final RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        adapter = new ChallengeAdapter();

        registerBT = rootView.findViewById(R.id.registerBT);
        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.fragment_change("register");
            }
        });


        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        adapter.addItem(new Challenge(1,"protect","protect animals"));
        adapter.addItem(new Challenge(2,"water","reduce using water"));

        recyclerView.setAdapter(adapter);



        return rootView;
    }
}
