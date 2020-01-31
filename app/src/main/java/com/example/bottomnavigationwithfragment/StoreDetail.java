package com.example.bottomnavigationwithfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

public class StoreDetail extends AppCompatActivity {

    TextView storename,storetime,storetoday;
    SingletonHolder singletonHolder = SingletonHolder.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);

        Intent intent = getIntent();
        String sotrename1 = intent.getStringExtra("storeName");
        String storetime1= intent.getStringExtra("storeTime");
        String storetoday1 = intent.getStringExtra("storeToday");

        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(sotrename1);
        setSupportActionBar(tb) ;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24px);


        storename=(TextView)findViewById(R.id.storeName);
        storetime=(TextView)findViewById(R.id.storeTime);
        storetoday=(TextView)findViewById(R.id.storeToday);

        storename.setText(sotrename1);
        storetime.setText(storetime1);
        storetoday.setText(storetoday1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
            case R.id.favorite:
//                Intent intent1 = new Intent(this,MainActivity.class);
//                intent1.putExtra("storeName",storename.getText());
//                intent1.putExtra("storeTime",storetime.getText());
//                intent1.putExtra("storeToday",storetoday.getText());
//                startActivity(intent1);
//                Toast.makeText(getApplicationContext(),"값이 전달됨"+storename.getText(),Toast.LENGTH_LONG).show();
                if(!singletonHolder.favoriteArrayList.contains(storename.getText())){
                    singletonHolder.favoriteArrayList.add(new StoreItem("null",storename.getText().toString(),storetime.getText().toString(),storetoday.getText().toString()));
                    Toast.makeText(getApplicationContext(),storename.getText()+"이 즐겨찾기에 추가되었습니다.",Toast.LENGTH_LONG).show();
                    Log.e("arr",singletonHolder.favoriteArrayList.toString());
                }
                else if(singletonHolder.favoriteArrayList.contains(storename.getText())){
                    Toast.makeText(getApplicationContext(),storename.getText()+"은 이미 추가되었습니다.",Toast.LENGTH_LONG).show();
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

}
