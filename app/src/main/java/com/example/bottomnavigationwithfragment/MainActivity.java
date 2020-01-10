package com.example.bottomnavigationwithfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.bottomnavigationwithfragment.fragment.FavoriteFragment;
import com.example.bottomnavigationwithfragment.fragment.HomeFragment;
import com.example.bottomnavigationwithfragment.fragment.MyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    Fragment homeFragment,favoriteFragment,myFragment;
    BottomNavigationView bottomNavigationView;
    //FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
                        if (favoriteFragment != null) { fragmentManager.beginTransaction().show(favoriteFragment).commit();}
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
    }

}
