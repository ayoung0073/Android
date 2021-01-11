package com.moonayoung.captureintent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    ImageView imageView;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.imageView);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
    }

    @Override
    public void onDenied(int i, String[] permissions) {
        Toast.makeText(this,"permissions denied: "+permissions.length,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int i, String[] permissions) {
        Toast.makeText(this,"permissions granted: "+permissions.length,Toast.LENGTH_LONG).show();

    }

    public void takePicture(){//사진파일을 여는게 아니라 파일의 위치를 얻어서 파일을 읽도록 만들 것
        if (file==null)
        {
            file=createFile();
        }

        Uri fileUri= FileProvider.getUriForFile(this, "com.moonayoung.captureintent.fileprovider",file);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //이미 만들어진 상수로
        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
        if(intent.resolveActivity(getPackageManager())!=null){ //액티비티가 있냐 확인하는 것
            startActivityForResult(intent,101);
        }

    }

    private File createFile(){
        String filename="capture.jpg";

        File storageDir= Environment.getExternalStorageDirectory();
        File outFile=new File(storageDir,filename);

        return outFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101 && resultCode==RESULT_OK)
        {
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inSampleSize=6;
            Bitmap bitmap=BitmapFactory.decodeFile(file.getAbsolutePath(),options); //이미지 샘플링(일정한비율로 크기 줄여줌, 손실없이..)

            imageView.setImageBitmap(bitmap);
        }
    }
}