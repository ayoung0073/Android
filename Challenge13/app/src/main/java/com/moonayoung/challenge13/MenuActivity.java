package com.moonayoung.challenge13;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button profileBT;
    Button plannerBT;
    Button diaryBT;
    Button studyBT;
    Button guestBT;

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        profileBT=findViewById(R.id.profileBT);
        plannerBT=findViewById(R.id.plannerBT);
        diaryBT=findViewById(R.id.diaryBT);
        studyBT=findViewById(R.id.studyBT);
        guestBT=findViewById(R.id.guestBT);

        profileBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent=new Intent(getApplicationContext(),ProfileFragment.class); //데이터 전달 안해도돼서 안씀
//                startActivityForResult(intent,101);

                ProfileFragment profileFragment= new ProfileFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.container,profileFragment).commit();
            }
        });

        plannerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerFragment plannerFragment=new PlannerFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.container,plannerFragment).commit();
            }
        });

        diaryBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaryFragment diaryFragment=new DiaryFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.container,diaryFragment).commit();
            }
        });


        studyBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudyListFragment studyListFragment=new StudyListFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.container,studyListFragment).commit();
            }
        });
        guestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonFragment personFragment=new PersonFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.container,personFragment).commit();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void onFragmentChanged(String str){
        UrlFragment urlFragment=new UrlFragment();
        DiaryListFragment listFragment=new DiaryListFragment();
        DiaryFragment diaryFragment=new DiaryFragment();


//      Intent intent=new Intent(getApplicationContext(),UrlFragment.class);
        Bundle bundle=new Bundle();

        //getSupportFragmentManager().beginTransaction().replace(R.id.container, urlFragment).commit();
        bundle.putString("type",str);
        if(str.equals("blog")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,urlFragment).commit();
            bundle.putString("type","blog");
        }else if(str.equals("insta")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,urlFragment).commit();
            bundle.putString("type","insta");
        }else if(str.equals("study_webHacking")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,urlFragment).commit();
            bundle.putString("type","study_webHacking");
        }else if(str.equals("study_network")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,urlFragment).commit();
            bundle.putString("type","study_network");
        }else if(str.equals("diaryList")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,listFragment).commit();
        }else if(str.equals("diary")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,diaryFragment).commit();
        }

        urlFragment.setArguments(bundle);
    }


}
