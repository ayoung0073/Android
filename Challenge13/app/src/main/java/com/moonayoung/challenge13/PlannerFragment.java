package com.moonayoung.challenge13;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.GregorianCalendar;


public class PlannerFragment extends Fragment {

    //GregorianCalendar today = new GregorianCalendar();
    //today_year = today.get(today.YEAR); 이런식으로 오늘 날짜 불러오기

    int today_year, today_month, today_day;

    TextView calText;
    Button homeBT;
    Button calBackBT, calNextBT;

    TextView[][] cals = new TextView[6][7];

    String[] months = {"2020년 1월", "2020년 2월","2020년 3월","2020년 4월","2020년 5월","2020년 6월",
                        "2020년 7월","2020년 8월","2020년 9월","2020년 10월","2020년 11월","2020년 12월"};
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_planner, container,false);

        calText = rootView.findViewById(R.id.calText);

        spinner=rootView.findViewById(R.id.spinner);

        calBackBT=rootView.findViewById(R.id.calBackBT);
        calNextBT=rootView.findViewById(R.id.calNextBT);

        homeBT=rootView.findViewById(R.id.homeBT6);
        homeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MenuActivity.class);
                startActivity(intent);
            }
        });

        calBackBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedText = calText.getText().toString();
                if(!selectedText.equals("2020년 1월")){
                    Character ch=selectedText.charAt(selectedText.indexOf("월")-1);
                    if(ch>'0' && ch<='9') {
                        Character ch_ = (char) ((int) ch - 1);

                        String chg = String.valueOf(ch);

                        String chgText = selectedText.replace(chg + "월", String.valueOf(ch_) + "월");
                        calText.setText(chgText);
                    }
                }
            }
        });

        calNextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedText = calText.getText().toString();
                if(!selectedText.equals("2020년 12월")){
                    Character ch=selectedText.charAt(selectedText.indexOf("월")-1);
                    if(ch>='0' && ch<='9') {
                        Character ch_ = (char) ((int) ch + 1);

                        String chg = String.valueOf(ch);

                        String chgText = selectedText.replace(chg + "월", String.valueOf(ch_) + "월");
                        calText.setText(chgText);
                    }
                }
            }
        });

        GregorianCalendar today = new GregorianCalendar();
        today_year = today.get(today.YEAR);
        today_month = today.get(today.MONTH);
        today_day = today.get(today.DAY_OF_MONTH);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
            getContext(),
            android.R.layout.simple_spinner_item,
            months
            );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calText.setText(months[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                calText.setText(today_year+"년 "+today_month+"월");
            }
        });
        return rootView;
    }

}
