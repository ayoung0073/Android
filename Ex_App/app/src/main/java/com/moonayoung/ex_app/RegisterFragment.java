package com.moonayoung.ex_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.pedro.library.AutoPermissions;

import java.io.File;
import java.sql.SQLException;

public class RegisterFragment extends Fragment {
    ImageView contentView;
    Button cameraBT;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_register, container, false);

        contentView = rootView.findViewById(R.id.contentView);
        Toast.makeText(getContext(),"register 띄움",Toast.LENGTH_LONG).show();

        cameraBT = rootView.findViewById(R.id.cameraBT);
        cameraBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //((MainActivity)getActivity()).takePicture(); // 프래그먼트에서 액티비티 호출

                Intent intent = new Intent(getContext(),CameraActivity.class);
                startActivityForResult(intent, 101);
                //Toast.makeText(mainActivity, "액티비티 호출", Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText((MainActivity)getActivity(),"사진띄우는화면으로 돌아옴",Toast.LENGTH_LONG).show();
        if(requestCode==101){
            if(data!=null)
            {
                byte[] bytes = data.getByteArrayExtra("image");
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                contentView.setImageBitmap(bitmap);

            }
        }

    }

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
    }


}
