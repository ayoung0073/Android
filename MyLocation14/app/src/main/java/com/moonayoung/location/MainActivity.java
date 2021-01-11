package com.moonayoung.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationService();
            }
        });
        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    //(내부 클래스로)위치 리스너 구현 -> 위치 관리자에서 전달하는 위치 정보를 받기 위해 정의된 인터페이스. onLocationChanged() 메서드 구현
    class GPSListener implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            String message = "< 내 위치 >\nLatitude : " + latitude + "\nLongitude : " + longitude;
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }
        public void startLocationService() {
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        // LocationManager로  형변환 해주기

        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String message = "< 최근 위치 >\nLatitude : " + latitude + "\nLongitude : " + longitude;

                textView.setText(message);
            }

            // 위치 관리자는 일정한 간격으로 위치 정보를 확인하거나 일정 거리 이상을 이동했을 때 위치 정보를 전달하는 기능 D
            // 위치 관리자에게 현재 위치를 알려달라고 요청하기 위핸 requestLocationUpdates() 메서드를 호출해야함
            // 파라미터 -> 최소 시간, 최소 거리, 위치 리스너 객체
            GPSListener gpsListener = new GPSListener();
            long minTime = 1000; //최소 시간
            float minDistance = 0; //최소 거리
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,gpsListener);
            Toast.makeText(getApplicationContext(),"위치확인 요청",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
