package com.moonayoung.receiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoPermissions.Companion.loadAllPermissions(this,101); // 위험권한있을 때 알아서 사용자에게 요청하는 것. 코드는 어떤거 넣어도됨


    }

    @Override
    public void onDenied(int requestCode, String[] permissions) {
        showToast("권한 거부된 것: "+ permissions.length); //몇개 거부됐는지 알 수 있음.
    }

    @Override
    public void onGranted(int requestCode, String[] permissions) {
        showToast("권한 허용된 것: "+permissions.length);
    }

    public void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //위험권한을 사용자에게 요청하고 나서 사용자가 허가했는지 거부했는지 알 수 있는 메서드.
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
    }
}