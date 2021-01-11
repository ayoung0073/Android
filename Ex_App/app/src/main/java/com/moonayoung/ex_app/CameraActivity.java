package com.moonayoung.ex_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class CameraActivity extends AppCompatActivity implements AutoPermissionsListener {

    File file;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

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
        AutoPermissions.Companion.loadAllPermissions(this,101);

}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //    AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,);
    }


    public File createFile(){
        String filename="capture.jpg";

        File storageDir = Environment.getExternalStorageDirectory();
        File outFile = new File(storageDir, filename);

        return outFile;
    }


    @Override // 카메라 찍고 돌아오면 register프래그먼트에 이미지 전달
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101 && resultCode==RESULT_OK)
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize= 2;

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Bitmap bitmap= BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            bitmap.compress(Bitmap.CompressFormat.JPEG,40, outputStream);
            byte[] bytes = outputStream.toByteArray();
            Intent intent = new Intent();
            intent.putExtra("image",bytes);
            System.out.println("image put한거까지 성공");
            setResult(RESULT_OK,intent);
            System.out.println("intent setResult함?");
/*여기까지 함*/
            finish();
            //Bundle bundle = new Bundle();



           /* if(bitmap != null || registerFragment.contentView!=null) registerFragment.contentView.setImageBitmap(bitmap);
            else Toast.makeText(getApplicationContext(), "bitmap null", Toast.LENGTH_SHORT).show();*/
            //registerFragment.setArguments(bundle);
        }
    }

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }
}
