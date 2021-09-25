package com.example.hireme.frontend.it20133290;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hireme.R;

public class IT20133290_CustomerMenu extends AppCompatActivity {

    Button btn1, btn2, btn3;
    String msg;
    TextView top, bottom;
    ImageView topImg, bottomImg, backArrow;
    Animation topAnim, bottomAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_it20133290_customer_menu);

        btn1 = findViewById(R.id.btnAddVaMenu);
        btn2 = findViewById(R.id.btnPublishedVaMenu);
        //btn3 = findViewById(R.id.btnInterviewMenu);
        backArrow = findViewById(R.id.backArrow);

        top = findViewById(R.id.tvCustomerMenuAddTitle);
        bottom = findViewById(R.id.tvCustomerMenuMyVacancyTitle);

        topImg = findViewById(R.id.ivCustomerMenuAddTitle);
        bottomImg = findViewById(R.id.ivCustomerMenuMyVacancyTitle);


        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        topImg.setAnimation(bottomAnim);
        btn1.setAnimation(topAnim);
        top.setAnimation(topAnim);

        bottomImg.setAnimation(bottomAnim);
        btn2.setAnimation(topAnim);
        bottom.setAnimation(topAnim);

        Intent i = getIntent();
        msg = i.getStringExtra("email");

        System.out.println(msg);


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void onClick(View v){
        Intent i = new Intent();

        if(v == btn1) {
            i = new Intent(this, IT20133290_AddVacancy.class);
            i.putExtra("email",msg);

        }else if(v == btn2) {
            i = new Intent(this, IT20133290_PublishedVacancy.class);
            i.putExtra("email",msg);
        }
//        else if(v == btn3)
//            i = new Intent(this,IT20133290_WorkerRequest.class);

        i.putExtra("email",msg);
        startActivity(i);
    }


}