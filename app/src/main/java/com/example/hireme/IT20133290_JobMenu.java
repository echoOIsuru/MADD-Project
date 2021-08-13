package com.example.hireme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IT20133290_JobMenu extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20133290_job_menu);
        btn1 = findViewById(R.id.btnLatestV);
        btn2 = findViewById(R.id.btnFindV);
        btn3 = findViewById(R.id.btnSentR);
        btn4 = findViewById(R.id.btnAcceptedV);

    }

    public void Onclick(View v){
        Intent i = new Intent(this,IT20133290_LatestVacancy.class);
        System.out.println(v);

        if(v == btn1){
            i = new Intent(this,IT20133290_LatestVacancy.class);
        }else if(v == btn2){
            i = new Intent(this,IT20133290_FindVacancy.class);
        }else if(v == btn3){
            i = new Intent(this,IT20133290_SentRequest.class);
        }else if(v == btn4){
            i = new Intent(this,IT20133290_LatestVacancy.class);
        }

        startActivity(i);
    }

}

