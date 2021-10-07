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

public class IT20133290_JobMenu extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;
    ImageView img;
    TextView tv,tv2;
    Animation topAnim, bottomAnim;
    ImageView backArrow;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_it20133290_job_menu);
        btn1 = findViewById(R.id.btnLatestV);
        img = findViewById(R.id.ivVacancyMenuImage);
        tv = findViewById(R.id.tvVacancyMenuTitle);
        tv2 = findViewById(R.id.tvVacancyMenuDescription);
        backArrow = findViewById(R.id.backArrow);
//        btn2 = findViewById(R.id.btnFindV);
//        btn3 = findViewById(R.id.btnSentR);
//        btn4 = findViewById(R.id.btnAcceptedV);


        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        img.setAnimation(topAnim);
        btn1.setAnimation(bottomAnim);
        tv.setAnimation(bottomAnim);
        tv2.setAnimation(bottomAnim);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void Onclick(View v){
        Intent i = new Intent(this,IT20133290_LatestVacancy.class);

        if(v == btn1){
            i = new Intent(this,IT20133290_LatestVacancy.class);
        }else if(v == btn2){
            i = new Intent(this,IT20133290_FindVacancy.class);
        }else if(v == btn3){
            i = new Intent(this,IT20133290_SentRequest.class);
        }else if(v == btn4){
            i = new Intent(this, IT20133290_AcceptedRequest.class);
        }

        startActivity(i);
    }

}

