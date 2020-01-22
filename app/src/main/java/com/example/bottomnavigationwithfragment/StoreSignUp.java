package com.example.bottomnavigationwithfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.util.Calendar;

public class StoreSignUp extends AppCompatActivity {

    private ImageView img1,img2,img3;
    private final int GET_GALLERY_IMAGE1 = 200;
    private final int GET_GALLERY_IMAGE2 = 201;
    private final int GET_GALLERY_IMAGE3 = 202;

    private EditText edit1,edit2,edit3,edit4,edit5,edit6,edit7,edit8;
    TimePickerDialog mTimePicker;
    Calendar mcurrentTime = Calendar.getInstance();
    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
    int minute = mcurrentTime.get(Calendar.MINUTE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_sign_up);

        img1=(ImageView)findViewById(R.id.image_1);
        img2=(ImageView)findViewById(R.id.image_2);
        img3=(ImageView)findViewById(R.id.image_3);

        //이미지 1에 대한 클릭 리스너
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE1);
            }
        });

        //이미지 2에 대한 클릭 리스너
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE2);
            }
        });

        //이미지 3에 대한 클릭 리스너
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE3);
            }
        });


        edit1=(EditText)findViewById(R.id.store_name_insert);
        edit2=(EditText)findViewById(R.id.store_address_insert);
        edit3=(EditText)findViewById(R.id.store_starttime_insert);
        edit4=(EditText)findViewById(R.id.store_endtime_insert);
        edit5=(EditText)findViewById(R.id.store_phone_insert);
        edit6=(EditText)findViewById(R.id.store_number_insert);
        edit7=(EditText)findViewById(R.id.store_dae_insert);
        edit8=(EditText)findViewById(R.id.store_joong_insert);

//        edit3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("edit3click?","click");
//                mTimePicker = new TimePickerDialog(StoreSignUp.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                        String state = "오전";
//                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
//                        if (selectedHour > 12) {
//                            selectedHour -= 12;
//                            state = "오후";
//                        }
//                        // EditText에 출력할 형식 지정
//                        edit3.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
//                    }
//                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
//                mTimePicker.setTitle("Select Time");
//                mTimePicker.show();
//            }
//        });

        edit3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(edit3.hasFocus()){
                    mTimePicker = new TimePickerDialog(StoreSignUp.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            String state = "오전";
                            // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                            if (selectedHour > 12) {
                                selectedHour -= 12;
                                state = "오후";
                            }
                            // EditText에 출력할 형식 지정
                            edit3.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                            edit4.requestFocus();

                        }
                    }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                    mTimePicker.setTitle("오픈시간");
                    mTimePicker.show();
                }
            }

        });

        edit4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(edit4.hasFocus()){
                mTimePicker = new TimePickerDialog(StoreSignUp.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "오전";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "오후";
                        }
                        // EditText에 출력할 형식 지정
                        edit4.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                        edit5.requestFocus();
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("마감 시간");
                mTimePicker.show();
                }
            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            Log.e("이미지1주소",selectedImageUri+"");
            img1.setImageURI(selectedImageUri);

        }
        else if(requestCode == GET_GALLERY_IMAGE2 && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri selectedImageUri = data.getData();
            img2.setImageURI(selectedImageUri);
        } else if(requestCode == GET_GALLERY_IMAGE3 && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri selectedImageUri = data.getData();
            img3.setImageURI(selectedImageUri);
        }

    }
}
