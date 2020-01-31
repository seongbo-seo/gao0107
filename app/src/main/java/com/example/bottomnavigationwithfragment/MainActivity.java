package com.example.bottomnavigationwithfragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bottomnavigationwithfragment.fragment.FavoriteFragment;
import com.example.bottomnavigationwithfragment.fragment.HomeFragment;
import com.example.bottomnavigationwithfragment.fragment.MyFragment;
import com.example.bottomnavigationwithfragment.juso.JusoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    Fragment homeFragment,favoriteFragment,myFragment;
    BottomNavigationView bottomNavigationView;
    //FragmentTransaction fragmentTransaction;
    TextView juso;
    ImageView jusoarrow;
    String name,time,today;
    int juso_button_code = 1212;




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==juso_button_code && resultCode == RESULT_OK){
            String juso_name = data.getStringExtra("juso");
            juso.setText(juso_name);
        }else if(requestCode==juso_button_code && resultCode == 1213){
            String juso_name = data.getStringExtra("juso_list");
            juso.setText(juso_name);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final Intent intent = getIntent();
//        String juso1 = intent.getStringExtra("juso");
//        String name =intent.getStringExtra("storeName");
//        String time =intent.getStringExtra("storeTime");
//        String today=intent.getStringExtra("storeToday");


        fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.frame,homeFragment).commit();

        bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:

                        if (homeFragment != null) { fragmentManager.beginTransaction().show(homeFragment).commit();}
                        if (favoriteFragment != null) { fragmentManager.beginTransaction().hide(favoriteFragment).commit();}
                        if (myFragment != null) { fragmentManager.beginTransaction().hide(myFragment).commit();}
                        return true;

                    case R.id.navigation_favorite:

                        if (favoriteFragment == null) {
                            favoriteFragment = new FavoriteFragment();
                            fragmentManager.beginTransaction().add(R.id.frame, favoriteFragment).commit();
                        }
                        if (homeFragment != null) { fragmentManager.beginTransaction().hide(homeFragment).commit();}
                        if (favoriteFragment != null) { fragmentManager.beginTransaction().show(favoriteFragment).commit();
                           }
                        if (myFragment != null) { fragmentManager.beginTransaction().hide(myFragment).commit();}
                        return true;

                    case R.id.navigation_my:
                        if (myFragment == null) {
                            myFragment = new MyFragment();
                            fragmentManager.beginTransaction().add(R.id.frame, myFragment).commit();
                        }
                        if (homeFragment != null) { fragmentManager.beginTransaction().hide(homeFragment).commit();}
                        if (favoriteFragment != null) { fragmentManager.beginTransaction().hide(favoriteFragment).commit();}
                        if (myFragment != null) { fragmentManager.beginTransaction().show(myFragment).commit();}
                        return true;
                }
                return false;
            }
        });


                juso=(TextView)findViewById(R.id.juso);
//                if(juso1!=null){
//                juso.setText(juso1);
//                }
                jusoarrow=(ImageView)findViewById(R.id.juso_arrow);

                juso.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), JusoActivity.class);
//                        startActivity(intent);
                        Intent intent2 = new Intent(MainActivity.this,JusoActivity.class);
                        startActivityForResult(intent2,juso_button_code);
                    }
                });

                jusoarrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(),JusoActivity.class);
//                        startActivity(intent);
                        Intent intent2 = new Intent(MainActivity.this,JusoActivity.class);
                        startActivityForResult(intent2,juso_button_code);
                    }
                });



    }

}
