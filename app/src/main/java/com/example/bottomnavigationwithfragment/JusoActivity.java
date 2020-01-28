package com.example.bottomnavigationwithfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bottomnavigationwithfragment.retrofit.RetrofitConnection;
import com.example.bottomnavigationwithfragment.retrofit.RetrofitInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JusoActivity extends AppCompatActivity {

    ImageView img1,img2;
    LinearLayout linearLayout;
    TextView textView;
    LocationManager locationManager;
    EditText juso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juso);
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        img1=(ImageView)findViewById(R.id.cancel_btn);
        img2=(ImageView)findViewById(R.id.search_btn);
        linearLayout=(LinearLayout)findViewById(R.id.mylocation);
        textView =(TextView)findViewById(R.id.juso_result);
        juso = (EditText)findViewById(R.id.juso_edit);


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions( JusoActivity.this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                            0 );
                }
                else{
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    String provider = location.getProvider();
                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();
                    double altitude = location.getAltitude();
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            1000,
                            1,
                            gpsLocationListener);
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            1000,
                            1,
                            gpsLocationListener);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("longitude",longitude+"");
                    startActivity(intent);
                }

            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String juso1 = juso.getText().toString();
                //주소를 가져오는 레트로 핏
                RetrofitConnection retrofitConnection = new RetrofitConnection();
                RetrofitInterface retrofitInterface = retrofitConnection.retrofit1.create(RetrofitInterface.class);
                retrofitInterface.getAddress(juso1).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.e("카카오성공","성공");
                        Log.e("body content",response.body().toString());
                        JsonParser jsonParser = new JsonParser();
                        JsonObject jsonObject = (JsonObject)jsonParser.parse(response.body().toString());
                        JsonArray docu = (JsonArray)jsonObject.get("documents");
                        int num = docu.size();
                        Log.e("배열의 크기",num+"");
                        if(num==0){
                            textView.setText("찾는 주소가 없습니다.");
                        }
                        for(int i =0;i<num;i++){
                            JsonObject docu1 = (JsonObject)docu.get(i);
                            textView.setText(docu1.get("address_name").toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e("카카오실패","실패");
                        Log.e("카카오실패이유",t.toString());
                    }
                });
            }
        });


    }
    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();

            textView.setText("위치정보 : " + provider + "\n" +
                    "위도 : " + longitude + "\n" +
                    "경도 : " + latitude + "\n" +
                    "고도  : " + altitude);
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };
}
