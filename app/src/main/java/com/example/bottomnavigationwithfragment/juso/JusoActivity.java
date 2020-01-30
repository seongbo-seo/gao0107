package com.example.bottomnavigationwithfragment.juso;

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
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bottomnavigationwithfragment.MainActivity;
import com.example.bottomnavigationwithfragment.R;
import com.example.bottomnavigationwithfragment.retrofit.RetrofitConnection;
import com.example.bottomnavigationwithfragment.retrofit.RetrofitInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JusoActivity extends AppCompatActivity {

    ImageView img1,img2;
    LinearLayout linearLayout;
    TextView textView;
    LocationManager locationManager;
    EditText juso;
    ListView listView;
    int page = 1;
    ArrayList<JusoItem> jusoItemArrayList;
    String juso1=null;
    int size=0;
    String su =null;

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
        listView = (ListView)findViewById(R.id.juso_listview);

        final InputMethodManager imm;
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        RetrofitConnection retrofitConnection = new RetrofitConnection();
        final RetrofitInterface retrofitInterface = retrofitConnection.retrofit1.create(RetrofitInterface.class);



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
                    String x= longitude+"";
                    String y= latitude+"";
                    String coord="WGS84";
                    RetrofitConnection retrofitConnection = new RetrofitConnection();
                    RetrofitInterface retrofitInterface = retrofitConnection.retrofit1.create(RetrofitInterface.class);
                    retrofitInterface.transform(x,y,coord).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            JsonParser jsonParser = new JsonParser();
                            JsonObject jsonObject = (JsonObject)jsonParser.parse(response.body().toString());
                            JsonArray docu = (JsonArray)jsonObject.get("documents");
                            JsonObject docu1=(JsonObject)docu.get(0);
                            Log.e("좌표",docu1.toString());
                            if(docu1.get("road_address").isJsonObject()){
                            JsonObject docu2=(JsonObject)docu1.get("road_address");
                                String transJuso = docu2.get("address_name").toString();
                                transJuso = transJuso.replace("\"","");
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("juso",transJuso);
                                startActivity(intent);
                            }else{
                                JsonObject docu2=(JsonObject)docu1.get("address");
                                String transJuso = docu2.get("address_name").toString();
                                transJuso = transJuso.replace("\"","");
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("juso",transJuso);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });

                }

            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                juso1 = juso.getText().toString().trim();
                imm.hideSoftInputFromWindow(juso.getWindowToken(),0);
                size =15;
                if(juso1.isEmpty()){
                    juso.setError("주소를 입력하세요");
                    juso.requestFocus();
                }else{
                //주소를 가져오는 레트로 핏

                retrofitInterface.getAddress(juso1,page,size).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.e("카카오성공","성공");
                        Log.e("body content",response.body().toString());
                        JsonParser jsonParser = new JsonParser();
                        JsonObject jsonObject = (JsonObject)jsonParser.parse(response.body().toString());
                        JsonArray docu = (JsonArray)jsonObject.get("documents");
                        JsonObject meta = (JsonObject)jsonObject.get("meta");
                        su =meta.get("is_end")+"";
                        Log.e("총 개수",su);
                        int num = docu.size();
                        Log.e("배열의 크기",num+"");
                        if(num==0){
                            textView.setText("찾는 주소가 없습니다.");
                        }
                        jusoItemArrayList = new ArrayList<JusoItem>();
                        for(int i =0;i<num;i++){
                            JsonObject docu1 = (JsonObject)docu.get(i);
                            JsonObject docu2 = (JsonObject) docu1.get("road_address");
                            String mainJuso = docu1.get("address_name").toString();
                            String subJuso = docu2.get("address_name").toString();
                            mainJuso =mainJuso.replace("\"","");
                            subJuso=subJuso.replace("\"","");
                            jusoItemArrayList.add(new JusoItem(mainJuso,subJuso));
                        }
                        if(su.equals("false")){
                            page++;
                            Log.e("if문 통과","pass");
                            Log.e("page",page+"");

                            retrofitInterface.getAddress(juso1,page,size).enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    Log.e("카카오성공","성공");
                                    Log.e("body content",response.body().toString());
                                    JsonParser jsonParser = new JsonParser();
                                    JsonObject jsonObject = (JsonObject)jsonParser.parse(response.body().toString());
                                    JsonArray docu = (JsonArray)jsonObject.get("documents");
                                    JsonObject meta = (JsonObject)jsonObject.get("meta");
                                    su =meta.get("is_end")+"";
                                    Log.e("총 개수",su);
                                    int num = docu.size();
                                    Log.e("배열의 크기",num+"");
                                    if(num==0){
                                        textView.setText("찾는 주소가 없습니다.");
                                    }
                                    for(int i =0;i<num;i++){
                                        JsonObject docu1 = (JsonObject)docu.get(i);
                                        JsonObject docu2 = (JsonObject) docu1.get("road_address");
                                        String mainJuso = docu1.get("address_name").toString();
                                        String subJuso = docu2.get("address_name").toString();
                                        mainJuso =mainJuso.replace("\"","");
                                        subJuso=subJuso.replace("\"","");
                                        jusoItemArrayList.add(new JusoItem(mainJuso,subJuso));
                                    }

                                    Log.e("주소목록",jusoItemArrayList.toString());
                                    final JusoListAdapter jusoListAdapter = new JusoListAdapter(getApplicationContext(),jusoItemArrayList);
                                    listView.setAdapter(jusoListAdapter);
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            intent.putExtra("juso",jusoListAdapter.getItem(position).getMainJuso().toString());
                                            startActivity(intent);
                                        }
                                    });

                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {
                                    Log.e("카카오실패","실패");
                                    Log.e("카카오실패이유",t.toString());
                                }
                            });
                        }

                        Log.e("주소목록",jusoItemArrayList.toString());
                        final JusoListAdapter jusoListAdapter = new JusoListAdapter(getApplicationContext(),jusoItemArrayList);
                        listView.setAdapter(jusoListAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("juso",jusoListAdapter.getItem(position).getMainJuso().toString());
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e("카카오실패","실패");
                        Log.e("카카오실패이유",t.toString());
                    }
                });
             }
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
