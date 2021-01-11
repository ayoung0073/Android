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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    SupportMapFragment mapFragment;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Toast.makeText(getApplicationContext(),"지도 준비됨",Toast.LENGTH_LONG).show();
                map = googleMap;
            }
        });

        try{
            MapsInitializer.initialize(this);
        }catch (Exception e){
            e.printStackTrace();
        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationService();
            }
        });
        AutoPermissions.Companion.loadAllPermissions(this,101); //안돼서 추가함
        // 이걸 추가해야 지도 허용 알림 나옴
    }

    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(getApplicationContext(),"Permissions denied: "+strings.length,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(getApplicationContext(),"Permissions granted: "+strings.length,Toast.LENGTH_LONG).show();
    }

    class GPSListener implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            String message = "< 내 위치 >\nLatitude : " + latitude + "\nLongitude : " + longitude;
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            showCurrentLocation(latitude, longitude);
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }

    private void showCurrentLocation(Double latitude, Double longitude){
        LatLng curPoint = new LatLng(latitude,longitude);
        // 현재 위치의 좌표로 LatLng 객체 생성
        // LatLng 객체는 경위도 좌표로 구성된 위치를 지도에 표시할 수 있도록 정의된 객체
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,15));
        // 지정한 위치의 지도 영역 보여주기
        // animateCamer 메서드는 지도의 축척(Scale)을 지정할 수 있음
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

                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }

            // 위치 관리자는 일정한 간격으로 위치 정보를 확인하거나 일정 거리 이상을 이동했을 때 위치 정보를 전달하는 기능 D
            // 위치 관리자에게 현재 위치를 알려달라고 요청하기 위핸 requestLocationUpdates() 메서드를 호출해야함
            // 파라미터 -> 최소 시간, 최소 거리, 위치 리스너 객체
            GPSListener gpsListener = new GPSListener();
            long minTime = 10; //최소 시간
            float minDistance = 0; //최소 거리
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,gpsListener);
            Toast.makeText(getApplicationContext(),"위치확인 요청",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}