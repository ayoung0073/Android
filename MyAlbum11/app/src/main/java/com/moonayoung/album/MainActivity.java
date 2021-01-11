package com.moonayoung.album;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.InputStream;
//앨범에 접근하는 것이기 때문에 권한 부여해줘야함(manifest)
public class MainActivity extends AppCompatActivity{
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.imageView);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }
    //위험권한해조야댐.. 그 챕터 복붙학잉..
    public void openGallery()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        //MIME 타입이 image로 시작하는 데이터를 가져오기
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent,101);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 다른 화면으로부터 응답올 때 호출되는 메서드 오버라이딩
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101)
        {
            if(resultCode==RESULT_OK) //사진 선택일 때 정상 ok온다
            {
                Uri fileUri=data.getData();
                //이 intent의 getData() 메서드를 호출하면 Uri 자료형의 값이 반환됨
                //ContentResolver를 이용해 참조할 수 있는 이미지를 가리킴

                ContentResolver resolver=getContentResolver();
                try {
                    InputStream inputStream = resolver.openInputStream(fileUri);
                    //파라미터로 데이터 전달
                    Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                    // 데이터로 받은 파일을 비트맵객체로 리턴
                    imageView.setImageBitmap(bitmap);

                    inputStream.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}