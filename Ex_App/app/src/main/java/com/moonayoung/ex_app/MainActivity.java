package com.moonayoung.ex_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    IntroFragment introFragment = new IntroFragment();
    ChallengeFragment challengeFragment = new ChallengeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    RegisterFragment registerFragment = new RegisterFragment();

    File file;

    Button button;
    Button button2;
    Button button3;

    ViewPager container;

    static FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();

        button = findViewById(R.id.button_intro);
        button2 = findViewById(R.id.button_challenge);
        button3 = findViewById(R.id.button_profile);

        container = findViewById(R.id.container);
        container.setOffscreenPageLimit(3);

        Intent intent = getIntent();
        //Bundle bundle = new Bundle();
        //int index = bundle.getInt("index");
        int index = intent.getIntExtra("index", 1);

        Toast.makeText(getApplicationContext(), "index: " + index, Toast.LENGTH_LONG).show();
        class PagerAdapter extends FragmentStatePagerAdapter {
            ArrayList<Fragment> items = new ArrayList<Fragment>();

            public PagerAdapter(FragmentManager fm) {
                super(fm);
            }


            @NonNull
            @Override
            public Fragment getItem(int position) {
                return items.get(position);
            }

            @Override
            public int getCount() {
                return items.size();
            }

            public void addItem(Fragment item) {
                items.add(item);
            }
        }

        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addItem(introFragment);
        adapter.addItem(challengeFragment);
        adapter.addItem(profileFragment);

        container.setAdapter(adapter);



        if (index == 1) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.container, introFragment).commit();
            //adapter.getItem(0);
            container.setCurrentItem(0);
        } else if (index == 2) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.container, challengeFragment).commit();
            //adapter.getItem(1);
            container.setCurrentItem(1);
        } else if (index == 3) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
            //adapter.getItem(2);
            container.setCurrentItem(2);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.container, introFragment).commit();
                //adapter.getItem(0);
                container.setCurrentItem(0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.container, challengeFragment).commit();
                //adapter.getItem(1);
                container.setCurrentItem(1);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                //adapter.getItem(2);
                container.setCurrentItem(2);
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this,101);
    }


    public void fragment_change(String fragment){
        if(fragment.equals("register")) {
            manager.beginTransaction().replace(R.id.frame, registerFragment).commit();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //    AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,);
    }


    public void takePicture(){
        if(file==null){
            System.out.println("null");
            file=createFile();
        }

        Uri fileUri = FileProvider.getUriForFile(this, "com.moonayoung.ex_app.fileprovider", file);
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent2.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);

        if(intent2.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent2, 101);
        }

    }

    public File createFile(){
        String filename="capture.jpg";

        File storageDir = Environment.getExternalStorageDirectory();
        File outFile = new File(storageDir, filename);

        return outFile;
    }


  /*  @Override */// 카메라 찍고 돌아오면 register프래그먼트에 이미지 전달
/*
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101 && resultCode==RESULT_OK)
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize= 6;


            Bitmap bitmap= BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            if(bitmap == null) System.out.println("null");
            //Bundle bundle = new Bundle();


           // registerFragment.getImage(bitmap);
           */
/* if(bitmap != null || registerFragment.contentView!=null) registerFragment.contentView.setImageBitmap(bitmap);
            else Toast.makeText(getApplicationContext(), "bitmap null", Toast.LENGTH_SHORT).show();*//*

            //registerFragment.setArguments(bundle);
        }
    }
*/


    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this,"permissions denied: "+strings.length,Toast.LENGTH_LONG).show();

    }
    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this,"permissions granted: "+strings.length,Toast.LENGTH_LONG).show();

    }
}
